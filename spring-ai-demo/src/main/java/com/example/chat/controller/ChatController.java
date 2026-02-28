package com.example.chat.controller;

import com.example.chat.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Resource
    private ChatService chatService;

    @PostMapping
    public String chat(@RequestBody String message) {
        return chatService.chat(message);
    }

    // 如果需要流式响应
    @PostMapping(value = "/stream", produces = "text/event-stream")
    public Flux<String> streamChat(@RequestBody String message) {
        return chatService.streamChat(message);
    }
}
