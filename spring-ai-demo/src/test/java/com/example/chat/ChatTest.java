package com.example.chat;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootTest
public class ChatTest {

    private final static String MESSAGE = "给我用python写一个二分排序函数";

    @Resource
    private ChatController chatController;

    @Test
    public void chat() {
        System.out.println(chatController.chat(MESSAGE));
    }

    @Test
    public void streamChat() {
        Flux<String> flux = chatController.streamChat(MESSAGE);
        List<String> block = flux.collectList().block();
        Assertions.assertNotNull(block);
        block.forEach(System.out::print);
    }
}
