package com.stackroute.orderservice.config;

import com.stackroute.orderservice.dto.OrderDto;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class Producer {

    private static final String ROUTING_KEY="Order_routing key";
    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessageToRabbitMq(OrderDto orderDTO)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),ROUTING_KEY,orderDTO);
    }
}
