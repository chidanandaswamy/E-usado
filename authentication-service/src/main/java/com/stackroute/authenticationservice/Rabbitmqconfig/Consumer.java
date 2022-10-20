package com.stackroute.authenticationservice.Rabbitmqconfig;


import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.model.UserDTO;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private CustomUserDetailsService userService;

    @RabbitListener(queues = "User_queue")
    public void consumeProfile(UserDTO userDto) {

        System.out.println(userDto.toString());
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userService.register(user);
    }
}