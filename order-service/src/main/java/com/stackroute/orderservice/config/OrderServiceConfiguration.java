package com.stackroute.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConfiguration {


    public static final String QUEUE = "Order_queue";
    public static final String EXCHANGE = "Order_exchange";
    public static final String ROUTING_KEY = "Order_routing key";


//    public static final String CQUEUE = "Cart-queue";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE,false);
    }

//    @Bean
//    public Queue Cqueue(){
//        return new Queue(CQUEUE,false);
//    }


    @Bean
    public DirectExchange exchange(){

        return new DirectExchange(EXCHANGE);
    }




    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

//    @Bean
//    public Binding Cbinding(Queue Cqueue, DirectExchange exchange){
//        return BindingBuilder.bind(Cqueue).to(exchange).with(ROUTING_KEY);
//    }


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
