package com.stackroute.userservice.service;
import com.fasterxml.uuid.Generators;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.DbSequence;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber(String sequenceName)
    {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DbSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DbSequence.class);
        return !Objects.isNull(counter)?counter.getSeq() : 1;
    }

    

    HashSet<User> userList=new HashSet();
    @Override
    public void addUser(User user) {
        user.getAddress().setAddressID(Generators.timeBasedGenerator().generate());
        userRepository.save(user);

    }

    @Override
    public HashSet<User> findAllUsers() {
       userRepository.findAll().forEach(user -> userList.add(user));
       return userList;
    }

    @Override
    public User findByEmail(String email) {
//        User user = userRepository.findByEmail(email, HttpStatus.OK);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            System.out.println("hi");
            return user;

        }
        else{
            System.out.println("hello");
            throw new UserNotFoundException("user email doesn't exist");
        }


    }

   public User findByID(long id) {
      Optional<User>findid = userRepository.findById(id);
      if(findid.isPresent()){
           findid.get();
      }
      else{
          throw new UserNotFoundException("user id doesn't exist");
      }
       return findid.get();
   }

    @Override
    public ResponseEntity<String> updateProductById(long id,User user ){
        Optional<User> productOptional = userRepository.findById(id);
        if(productOptional.isPresent()){
            User savedProduct = userRepository.save(user);
            if(savedProduct != null ){
                return new ResponseEntity<>("Product with id " + id + " is updated", HttpStatus.ACCEPTED);
            } else {
                throw new UserNotFoundException("Product with id " + id + " is not found.So it can't be updated");

            }
        } else {
            throw new UserNotFoundException("Product with id " + id + " is not found.");
        }
    }


    @Override
    public ResponseEntity<String> deleteById(long id) {
        Optional<User> productOptional = userRepository.findById(id);
        if(productOptional.isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity<>("Product with id " + id + " deleted successfully", HttpStatus.OK);
        } else {
            throw new UserNotFoundException("Product with id " + id + " is not found.");
        }
    }



    }




