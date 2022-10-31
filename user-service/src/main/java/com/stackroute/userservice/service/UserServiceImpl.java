package com.stackroute.userservice.service;
import com.fasterxml.uuid.Generators;
import com.stackroute.userservice.config.Producer;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.model.UserDTO;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    Producer p;

    List<User> l=new ArrayList<>();

    @Override
    public User addUser(User user) {
        User user1=userRepository.findByEmail(user.getEmail());
        if(user1 != null && user1.getEmail()!=null){
            throw new UserNotFoundException("User with email " +user.getEmail() +" already exists.");
        }

        else {
            user.getAddress().setAddressID(Generators.timeBasedGenerator().generate());
            UserDTO u=new UserDTO();

            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.setName(user.getName());

            p.sendMessageToRabbitMq(u);
            return userRepository.save(user);

        }

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

    }

    @Override
    public User UpdateByEmail(User user,String email) {
        User u= userRepository.findByEmail(email);
        System.out.println(user);
        if(u!=null){
            user.getAddress().setAddressID(Generators.timeBasedGenerator().generate());
          return  userRepository.save(user);
        }

        else{throw new UserNotFoundException("User with email " + user + " doesn't exist.");}

    }


}



