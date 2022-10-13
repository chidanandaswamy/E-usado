package com.stackroute.orderservice.consumer;

import com.stackroute.orderservice.config.RabbitMqConfiguration;
import com.stackroute.orderservice.model.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;


@Component
public class Consumer {



    @RabbitListener(queues = RabbitMqConfiguration.QUEUE)
    public void rabitMqConfiguration(Product product) {
        System.out.println("Message received from queue : " + product);
    }
}
