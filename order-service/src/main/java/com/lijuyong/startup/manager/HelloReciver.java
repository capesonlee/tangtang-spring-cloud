package com.lijuyong.startup.manager;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by john on 2017/3/24.
 */
@Component
public class HelloReciver {
    @RabbitListener(queues = "hello")
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

    @RabbitListener(queues = "userQueue")
    public void myMessage(String msg) {
        System.out.println("message user:" + msg);
    }
}
