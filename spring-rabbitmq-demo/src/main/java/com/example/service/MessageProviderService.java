package com.example.service;

import com.example.entity.MsgEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProviderService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(MsgEntity entity) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(entity);
        rabbitTemplate.convertAndSend("testQueue", json);
    }
}
