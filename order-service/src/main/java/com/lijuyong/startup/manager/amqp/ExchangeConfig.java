package com.lijuyong.startup.manager.amqp;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Iterator;


/**
 * Created by john on 2017/3/24.
 */
@EnableRabbit
@Configuration
public class ExchangeConfig {

    @Bean
    TopicExchange johnTopicExchange(AmqpAdmin amqpAdmin){
        TopicExchange topicExchange =
                new TopicExchange("john-topic",true,true);
        amqpAdmin.declareExchange(topicExchange);
        return  topicExchange;
    }

    @Bean
    Queue userQueue(AmqpAdmin amqpAdmin){
        Queue queue = new Queue("userQueue",true);
        amqpAdmin.declareQueue(queue);
        return  queue;
    }

    @Bean
    Binding userQueueBindingToOrder(AmqpAdmin amqpAdmin,Queue userQueue,TopicExchange johnTopicExchange){
        Binding binding = BindingBuilder.bind(userQueue).to(johnTopicExchange).
                with("user.service");
        amqpAdmin.declareBinding(binding);
        return binding;
    }
    @Bean
    Binding userQueueBindingToSeq(AmqpAdmin amqpAdmin,Queue userQueue,TopicExchange johnTopicExchange){
        Binding binding = BindingBuilder.bind(userQueue).to(johnTopicExchange).
                with("seq.service");
        amqpAdmin.declareBinding(binding);
        return binding;
    }





}
