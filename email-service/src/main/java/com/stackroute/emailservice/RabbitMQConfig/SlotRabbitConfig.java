//package com.stackroute.emailservice.RabbitMQConfig;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SlotRabbitConfig {
//
//    public static final String QUEUE = "Slot_queue";
//    public static final String EXCHANGE = "Slot_exchange";
//    public static final String ROUTING_KEY = "Slot_routingkey";
//
//    @Bean
//    public Queue squeue(){
//        return new Queue(QUEUE,false);
//    }
//    @Bean
//    public DirectExchange sexchange(){
//
//        return new DirectExchange(EXCHANGE);
//    }
//    @Bean
//    public Binding sbinding(Queue queue, DirectExchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter sconverter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public AmqpTemplate stemplate(ConnectionFactory connectionFactory){
//        final RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(sconverter());
//        return rabbitTemplate;
//    }
//}
