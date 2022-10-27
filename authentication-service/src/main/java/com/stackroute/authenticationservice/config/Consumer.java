package com.stackroute.authenticationservice.config;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.service.UserServiceImpl;

@Component
public class Consumer {

    @Autowired
    private UserServiceImpl userService;

    @RabbitListener(queues = "User_queue")
    public void getUserDtoFromRabbitMq(UserDTO userDto) throws UserAlreadyExistsException {
        System.out.println(userDto.toString());
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userService.saveUser(user);
    }
}
