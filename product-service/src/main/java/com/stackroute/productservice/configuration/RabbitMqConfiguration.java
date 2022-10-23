package com.stackroute.productservice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${e-usado.product.rabbitmq.chat-queue}")
    String chatQueue;

    @Value("${e-usado.product.rabbitmq.mail-queue}")
    String mailQueue;

    @Value("${e-usado.product.rabbitmq.order-queue}")
    String orderQueue;

    @Value("${e-usado.product-service.rabbitmq.exchange}")
    private String exchange;

    @Value("${e-usado.product.rabbitmq.chat-routing-key}")
    private String chatRoutingKey;

    @Value("${e-usado.product.rabbitmq.mail-routing-key}")
    private String mailRoutingKey;

    @Value("${e-usado.product.rabbitmq.order-routing-key}")
    private String orderRoutingKey;

    @Bean
    public Queue chatQueue(){
        return new Queue(chatQueue);
    }

    @Bean
    public Queue mailQueue(){
        return new Queue(mailQueue);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding chatBinding(Queue chatQueue, TopicExchange exchange){
        return BindingBuilder.bind(chatQueue).to(exchange).with(chatRoutingKey);
    }

    @Bean
    public Binding mailBinding(Queue mailQueue, TopicExchange exchange){
        return BindingBuilder.bind(mailQueue).to(exchange).with(mailRoutingKey);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange exchange){
        return BindingBuilder.bind(orderQueue).to(exchange).with(orderRoutingKey);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
