package com.stackroute.userservice.controller;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private ResponseEntity responseEntity;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserServiceImpl userServiceImpl;


    @RequestMapping(value="/users/register" ,method=RequestMethod.POST)

    public ResponseEntity<?> register(@RequestBody User user) throws UserNotFoundException{

        responseEntity= new ResponseEntity<>(userServiceImpl.addUser(user), HttpStatus.OK);
        return responseEntity;
    }



    @RequestMapping(value="/users" ,method=RequestMethod.GET)

    public ResponseEntity<?> findAll() throws UserNotFoundException{

            responseEntity= new ResponseEntity<>(userServiceImpl.findAllUsers(), HttpStatus.OK);
         return responseEntity;
    }

    @RequestMapping (value="/users/{email}",method=RequestMethod.GET)
    public ResponseEntity<?> getByEmail(@PathVariable String email, HttpSession session) throws UserNotFoundException{

        responseEntity= new ResponseEntity<>(userServiceImpl.findByEmail(email), HttpStatus.CREATED);

        return responseEntity;

    }

    @RequestMapping(value="/users/delete/{email}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserByEmail( @PathVariable String email){
        responseEntity=new ResponseEntity<Boolean>(userServiceImpl.deleteUserByEmail(email),HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping(value="/users/update/{email}",method=RequestMethod.PUT)
    public ResponseEntity<User> updateUser( @RequestBody User user,@PathVariable String email){
        responseEntity=new ResponseEntity<User>(userServiceImpl.UpdateByEmail(user,email),HttpStatus.OK);
        return responseEntity;
    }

}
