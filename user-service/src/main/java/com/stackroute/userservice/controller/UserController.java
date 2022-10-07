package com.stackroute.userservice.controller;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;


@RestController
//@RequestMapping("/api/v1")
public class UserController {

    private ResponseEntity responseEntity;

    @Autowired
    UserServiceImpl userServiceImpl;


    @RequestMapping(value="/users/add",method = RequestMethod.POST)
    public void adduser(@RequestBody User user) {
        user.setUserId(userServiceImpl.getSequenceNumber(User.SEQUENCE_NAME));
        userServiceImpl.addUser(user);

    }


    @RequestMapping(value="/users" ,method=RequestMethod.GET)
    public HashSet<User> findAllUsers() {
        return userServiceImpl.findAllUsers();
    }



    @RequestMapping (value="/users/{email}",method=RequestMethod.GET)
    public ResponseEntity<?> getByEmail(@PathVariable String email, HttpSession session) throws UserNotFoundException{

        responseEntity= new ResponseEntity<>(userServiceImpl.findByEmail(email), HttpStatus.CREATED);

    return responseEntity;

    }

    @RequestMapping(value="/users/getById/{id}",method=RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable long id, HttpSession session) {
            responseEntity=new ResponseEntity<User>(userServiceImpl.findByID(id), HttpStatus.CREATED);
        return responseEntity;
    }



    @RequestMapping(value="/users/delete/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable long id){
        return userServiceImpl.deleteById(id);
    }


    @RequestMapping(value = "/users/update/{id}", method= RequestMethod.PUT)
    public ResponseEntity<?> updateProductById(@PathVariable long id, @RequestBody User user){
        return userServiceImpl.updateProductById(id, user);
    }

}
