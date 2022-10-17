package com.stackroute.userservice.service;
import com.fasterxml.uuid.Generators;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;




    @Override
    public void addUser(User user) {

        user.getAddress().setAddressID(Generators.timeBasedGenerator().generate());
        userRepository.save(user);

    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new UserNotFoundException("User Not Found !!");
        }
        return users;    }


    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getEmail()!=null) {
            return user;

        }
        else{
            throw new UserNotFoundException("user email doesn't exist");
        }
    }


    @Override
    public boolean deleteUserByEmail(String email) {
        User u= userRepository.findByEmail(email);
        if(u!=null && u.getEmail()!=null){
            userRepository.deleteByEmail(email);
    return true;
        }
          else{ throw new UserNotFoundException("User with email " + email + " doesn't exist.");}
//
    }

    @Override
    public User UpdateByEmail(User user,String email) {
        User u= userRepository.findByEmail(email);
        System.out.println(user);
        if(u!=null &&u.getEmail()!=null  ){
            user.getAddress().setAddressID(Generators.timeBasedGenerator().generate());
          return  userRepository.save(user);
        }

        else{throw new UserNotFoundException("User with email " + user + " doesn't exist.");}

    }

}



