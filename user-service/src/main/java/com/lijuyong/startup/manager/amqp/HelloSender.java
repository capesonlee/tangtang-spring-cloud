package com.lijuyong.startup.manager.amqp;

import com.lijuyong.startup.manager.dto.UserDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
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

    @Autowired
    RabbitMessagingTemplate rabbitMessagingTemplate;

    public void send() {
        String context = "Hello John  on " + new Date();
        rabbitTemplate.convertAndSend("hello",context);
    }

    public void sendViaExchange(String routingKey){
        String context ="A message from future user service";
        rabbitTemplate.convertAndSend("john-topic",routingKey,context);
    }
    public void sendObjectViaExchange(String routingKey){
        //String context ="A message from future user service";
        UserDTO userDTO = new UserDTO();
        userDTO.setId(12L);
        userDTO.setName("Lijuyong");
        userDTO.setOrdId("villia");

        rabbitMessagingTemplate.convertAndSend("john-topic",routingKey,userDTO);
    }

}
