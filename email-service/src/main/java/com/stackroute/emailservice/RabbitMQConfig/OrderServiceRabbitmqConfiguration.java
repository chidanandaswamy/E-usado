package com.stackroute.emailservice.RabbitMQConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceRabbitmqConfiguration {


    public static final String QUEUE = "Order_queue";
    public static final String EXCHANGE = "Order_exchange";
    public static final String ROUTING_KEY = "Order_routing key";


    @Bean
    public Queue Orderqueue(){
        return new Queue(QUEUE,false);
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
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
