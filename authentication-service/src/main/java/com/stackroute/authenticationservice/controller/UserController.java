package com.stackroute.authenticationservice.controller;


import com.stackroute.authenticationservice.domain.AuthRequest;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @GetMapping("/login")
    public String index() {
        OAuth2User user = getCurrentUser();
        return "Hello " + user.getAttributes().get("name") + ". Your email is " + user.getAttributes().get("email")
                + " and your profile picture is <img src='"+user.getAttributes().get("picture")+"' /> <br />"
                + "<a href='/logout'>logout</a>";
    }


    public OAuth2User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ((OAuth2AuthenticationToken)auth).getPrincipal();
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
        String loggedInUser =null;
        if(authRequest!=null){
            retrievedUser= userService.findByEmail(authRequest.getEmailId());
            loggedInUser=    retrievedUser.getEmail();
            if(retrievedUser!=null){
                retrievedUser.setPassword(authRequest.getChangePassword());
                userService.saveUser(retrievedUser);
            }else{
                throw new Exception("invalid username/password");
            }
        }else{
            throw new Exception("request is empty");
        }
        return loggedInUser + " "+ "password has been Changed Successfully";
    }



}
