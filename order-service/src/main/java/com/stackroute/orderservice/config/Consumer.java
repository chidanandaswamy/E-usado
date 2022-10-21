package com.stackroute.orderservice.config;


import rabbitmqdto.Product;
import com.stackroute.orderservice.service.CartServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    @Autowired
    CartServiceImpl cartServiceImpl;

    @RabbitListener(queues = "e-usado.product.rabbitmq.queue")
    public void getProducts(Product product) {
        System.out.println("Message received from queue : " + product);

    }
}
