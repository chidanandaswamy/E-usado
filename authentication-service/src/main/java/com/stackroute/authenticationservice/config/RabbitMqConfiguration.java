package com.stackroute.authenticationservice.config;

/*
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class MessageConfiguration {

    private String exchangeName="user_exchange";
    private String registerQueue="user_queue";


        @Bean
        public Jackson2JsonMessageConverter jsonMessageConverter()
        {
            return new Jackson2JsonMessageConverter();
        }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue registerQueue()
    {
        return new Queue(registerQueue,false);
    }

    @Bean
    Binding bindingUser(Queue registerQueue, DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueue()).to(exchange).with("user_routing");
    }

}*/

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class RabbitMqConfiguration  {


    public static final String QUEUE = "User_queue";
    public static final String EXCHANGE = "User_exchange";
    public static final String ROUTING_KEY = "User_routingkey";

    @Bean
    public Queue queue(){
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


