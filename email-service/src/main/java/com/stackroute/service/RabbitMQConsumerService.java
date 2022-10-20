package com.stackroute.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RabbitMQConsumerService {
    @RabbitListener(queues = "${e-usado.product.rabbitmq.queue}")
    public void recievedMessage(HashMap<String, Object> product) {
        System.out.println("Recieved Message From RabbitMQ: " + product);
    }
}
//    @RabbitListener(queues = "testQueue")
//    public void receiveMessage(HashMap<String, String> message) {
//        System.out.println("Received <" + message + ">");
//    }