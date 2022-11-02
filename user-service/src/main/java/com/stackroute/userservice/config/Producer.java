package com.stackroute.userservice.config;

import com.stackroute.userservice.model.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class Producer {

    private static final String ROUTING_KEY="User_routingkey";
    private static final String EMAIL_ROUTING_KEY="User_routingkey_email";
    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessageToRabbitMq(UserDTO userDTO)
    {
        System.out.println(exchange.getName());
        rabbitTemplate.convertAndSend(exchange.getName(),ROUTING_KEY,userDTO);
        rabbitTemplate.convertAndSend(exchange.getName(),EMAIL_ROUTING_KEY,userDTO);
    }

}