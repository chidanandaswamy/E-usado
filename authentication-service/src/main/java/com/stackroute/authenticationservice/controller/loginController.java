package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.model.AuthRequest;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.utill.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1")
public class loginController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;



    @PostMapping("/SaveUserDetails")
    public String saveUserDetails(@RequestBody User user) throws Exception {
        if (user!=null) {
            repository.save(user);

        }else{
            throw new Exception("User details is empty");
        }
        return "User Details saved successfully";
    }

    @GetMapping("/login")
    public String welcome() {
        return "Welcome to usado !!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        User retrievedUser=null;
        try {
            if (authRequest != null && authRequest.getUserName() != null) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            } else {
                retrievedUser= repository.findByEmailAndPassword(authRequest.getEmailId(),authRequest.getPassword());
            }
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(retrievedUser.getPassword());
    }
}

