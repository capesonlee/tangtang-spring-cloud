package com.lijuyong.startup.manager.amqp;

import com.lijuyong.startup.manager.dto.UserDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by john on 2017/3/24.
 */
@Component
public class HelloSender {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send() {
        String context = "Hello John  on " + new Date();
        amqpTemplate.convertAndSend("hello",context);
    }

    public void sendViaExchange(String routingKey){
        String context ="A message from future user service";
        amqpTemplate.convertAndSend("john-topic",routingKey,context);
    }
    public void sendObjectViaExchange(String routingKey){
        //String context ="A message from future user service";
        UserDTO userDTO = new UserDTO();
        userDTO.setId(12L);
        userDTO.setName("Lijuyong");
        userDTO.setOrdId("villia");

        Message message = MessageBuilder.withBody("foo".getBytes()).build();


        amqpTemplate.convertAndSend("john-topic",routingKey,userDTO,new MyMessagePostProcessor());
    }

}
