package com.lijuyong.startup.manager;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by john on 2017/3/24.
 */
@EnableRabbit
@Configuration
public class RabbitConfig {
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    @Bean
    TopicExchange johnTopicExchange(RabbitAdmin rabbitAdmin){
        TopicExchange topicExchange =
                new TopicExchange("john-topic",true,true);
        rabbitAdmin.declareExchange(topicExchange);
        return  topicExchange;
    }

    @Bean
    Queue userQueue(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue("seqQueue",true);
        rabbitAdmin.declareQueue(queue);
        return  queue;
    }

    @Bean
    Binding userQueueBindingToOrder(RabbitAdmin rabbitAdmin,Queue userQueue,TopicExchange johnTopicExchange){
        Binding binding = BindingBuilder.bind(userQueue).to(johnTopicExchange).
                with("user.service");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
    @Bean
    Binding userQueueBindingToSeq(RabbitAdmin rabbitAdmin,Queue userQueue,TopicExchange johnTopicExchange){
        Binding binding = BindingBuilder.bind(userQueue).to(johnTopicExchange).
                with("order.service");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }





}
