package com.stackroute.chatservice.service;

import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitMQConsumerService {

    @Autowired
    private ChatService service;

    /*@RabbitListener(queues = "productServiceQueue")
    public void receiveMessage(HashMap<String, Object> message) {
        System.out.println("Received <" + message + ">");
    }*/


    //product
    @RabbitListener(queues = "productChatQueue")
    public void ProductAddedConforming(Product request1) {



        System.out.println(request1);


        // service.sendEmailProductAdded(request1, model);
    }
}

