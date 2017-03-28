package com.lijuyong.startup.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijuyong.startup.manager.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2017/3/26.
 */
@Service
public class AmqpReciver {
    Logger logger = LoggerFactory.getLogger(this.getClass());
//    @RabbitListener(queues = "seqQueue")
//    public void myMessage(String msg) {
//        System.out.println("message user:" + msg);
//    }

    @RabbitListener(queues = "seqQueue")
    public void objectMessage(@Payload UserDTO msg, @Header("my-header") String my_header, Message message) {
        System.out.println("message user:" + msg.getName());
        String myHeader =(String) message.getMessageProperties().getHeaders().get("my-header");

        logger.info("Here is my header:" + myHeader );
        logger.info("my header from annotation:" +my_header);
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(msg);
            logger.info("here is the message recived: " + json);
        }
        catch (JsonProcessingException exc){
            logger.info("exceptions ",exc);
        }

    }
}
