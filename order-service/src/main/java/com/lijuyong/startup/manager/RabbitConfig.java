package com.lijuyong.startup.manager;


import org.springframework.amqp.core.Queue;
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
public class RabbitConfig {
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    Queue queue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("hello", false);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

}
