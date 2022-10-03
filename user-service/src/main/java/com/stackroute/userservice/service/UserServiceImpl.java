package com.stackroute.userservice.service;


import com.fasterxml.uuid.Generators;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public User findUserByEmail(String email) {

        User u = userRepository.findByEmail(email);
        if(u==null){
            throw new UserNotFoundException("User Not Found !!");
        }

            return u;


    }

    @Override
    public void deleteUserByEmail(String email) {

    }

    @Override
    public void deleteUser() {
        userRepository.deleteAll();

    }
}
