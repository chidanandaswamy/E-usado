package com.stackroute.authenticationservice.controller;


import com.stackroute.authenticationservice.domain.AuthRequest;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.security.SecurityTokenGenerator;
import com.stackroute.authenticationservice.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;



    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException
    {
        User retrievedUser = userService.findByEmailAndPassword(user.getEmail(),user.getPassword());

        if(retrievedUser==null)
        {
            throw new InvalidCredentialsException();
        }
        Map<String,String> map = securityTokenGenerator.generateToken(user);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PostMapping("/forgetPassword")
    public String forgotPassword(@RequestBody AuthRequest authRequest) throws Exception, UserAlreadyExistsException {
        User retrievedUser=null;
        if(authRequest!=null){
            retrievedUser= userService.findByEmailAndPassword(authRequest.getEmailId(),authRequest.getPassword());
            if(retrievedUser!=null){
                retrievedUser.setPassword(authRequest.getChangePassword());
                userService.saveUser(retrievedUser);
            }else{
                throw new Exception("invalid username/password");
            }
        }else{
            throw new Exception("request is empty");
        }
        return null;
    }



}
