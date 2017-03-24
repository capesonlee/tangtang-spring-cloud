package com.lijuyong.startup.manager.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by john on 2017/3/24.
 */
@Component
public class HelloSender {
    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "Hello John  on " + new Date();
        rabbitTemplate.convertAndSend("hello",context);
    }
}
