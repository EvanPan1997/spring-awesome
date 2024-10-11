package com.example.service;

import cn.hutool.json.JSONObject;
import com.example.entity.MsgEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class MessageConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "testQueue", containerFactory = "simpleRabbitListenerContainerFactory")
    public void receiveMessage(final Message message, Channel channel) throws IOException, TimeoutException {
        log.info("listener process start");
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body = message.getBody();
            MsgEntity entity = objectMapper.readValue(body, MsgEntity.class);
            channel.basicAck(deliveryTag, false);
            System.out.println("[listener]: " + new JSONObject(entity).toStringPretty());
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag, false, true);
        } finally {
            log.info("listener process finished");
        }
    }
}
