package com.lijuyong.startup.manager;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/3/26.
 */
@Service
public class AmqpReciver {
    @RabbitListener(queues = "seqQueue")
    public void myMessage(String msg) {
        System.out.println("message user:" + msg);
    }
}
