package com.stackroute.userservice.service;
import com.fasterxml.uuid.Generators;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;


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
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;

        }
        else{
            throw new UserNotFoundException("user email doesn't exist");
        }
    }


    @Override
    public User deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UserNotFoundException("User with email " + email + " doesn't exist.");
        }
            userRepository.deleteByEmail(email);
            return user;
    }

    @Override
    public User UpdateByEmail(User user,String email) {
        String email1 = user.getEmail();
        System.out.println(email1);
        if(email1==null){
            throw new UserNotFoundException("User with email " + user + " doesn't exist.");

        }

        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
     System.out.println(user);
         if(user==null){
            throw new UserNotFoundException("User with email " + user + " doesn't exist.");
        }
          userRepository.save(user);
             return user;
}


}




