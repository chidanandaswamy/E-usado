package com.stackroute.userservice.controller;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/add")
    public void adduser(@RequestBody User user) {
        userServiceImpl.addUser(user);
    }

    @GetMapping("/getUsers")
    public HashSet<User>  findAllUsers(){
        return userServiceImpl.findAllUsers();
    }

    @DeleteMapping("/delete")
    public void deleteAll(){
        userServiceImpl.deleteUser();
    }

    @GetMapping("/findByEmail/{email}")
    public User findUserByEmail(@RequestBody String email){
        return userServiceImpl.findUserByEmail(email);
    }

}
