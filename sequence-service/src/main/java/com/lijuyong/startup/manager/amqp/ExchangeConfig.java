package com.lijuyong.startup.manager.amqp;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by john on 2017/3/27.
 */
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
        Queue queue = new Queue("seqQueue",true);
        amqpAdmin.declareQueue(queue);
        return  queue;
    }


    @Bean
    Binding userQueueBindingToOrder(AmqpAdmin amqpAdmin, Queue userQueue, TopicExchange johnTopicExchange){
        Binding binding = BindingBuilder.bind(userQueue).to(johnTopicExchange).
                with("user.service");
        amqpAdmin.declareBinding(binding);
        return binding;
    }
    @Bean
    Binding userQueueBindingToSeq(AmqpAdmin amqpAdmin,Queue userQueue,TopicExchange johnTopicExchange){
        Binding binding = BindingBuilder.bind(userQueue).to(johnTopicExchange).
                with("order.service");
        amqpAdmin.declareBinding(binding);
        return binding;
    }

}
