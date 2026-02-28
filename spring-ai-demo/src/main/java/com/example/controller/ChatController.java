package com.example.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Resource
    private ChatClient chatClient;

    @PostMapping
    public String chat(@RequestBody String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    // 如果需要流式响应
    @PostMapping(value = "/stream", produces = "text/event-stream")
    public reactor.core.publisher.Flux<String> streamChat(@RequestBody String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}
