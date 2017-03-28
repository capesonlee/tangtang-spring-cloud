package com.lijuyong.startup.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijuyong.startup.manager.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by john on 2017/3/24.
 */
@Component
public class AmqpReciver {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @RabbitListener(queues = "userQueue")
    public void myMessage(UserDTO userDTO) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            logger.info("message user:" + objectMapper.writeValueAsString(userDTO));

        }
        catch (Exception exc){
            logger.error("出现了异常",exc);

        }


    }
}
