package com.example.service;

import cn.hutool.json.JSONObject;
import com.example.entity.MsgEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "testQueue")
    public void receiveMessage(String message) throws JsonProcessingException {
        MsgEntity entity = objectMapper.readValue(message, MsgEntity.class);
        System.out.println("[listener]: " + new JSONObject(entity).toStringPretty());
    }
}
