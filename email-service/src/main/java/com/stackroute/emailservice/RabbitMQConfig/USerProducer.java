//package com.stackroute.emailservice.RabbitMQConfig;
//
//import com.stackroute.emailservice.model.UserService;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class USerProducer {
//
//    private static final String ROUTING_KEY="User_routingkey";
//    private RabbitTemplate rabbitTemplate;
//    private DirectExchange exchange;
//
//    @Autowired
//    public USerProducer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
//        super();
//        this.rabbitTemplate = rabbitTemplate;
//        this.exchange = exchange;
//    }
//
//    public void sendMessageToRabbitMq(UserService userDTO)
//    {
//        rabbitTemplate.convertAndSend(exchange.getName(),ROUTING_KEY,userDTO);
//    }
//
//}