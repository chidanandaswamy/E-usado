package com.stackroute.userservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageConfiguration {

    public static final String QUEUE = "User_queue";

    public static final String EMAIL_QUEUE = "User_queue_email";
    public static final String EXCHANGE = "User_exchange";
    public static final String ROUTING_KEY = "User_routingkey";

    public static final String EMAIL_ROUTING_KEY = "User_routingkey_email";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE,false);
    }

    @Bean
    public Queue queueEmail(){
        return new Queue(EMAIL_QUEUE,false);
    }
    @Bean
    public DirectExchange exchange(){

        return new DirectExchange(EXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingEmail(Queue queueEmail, DirectExchange exchange){
        return BindingBuilder.bind(queueEmail).to(exchange).with(EMAIL_ROUTING_KEY);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}

