package com.example.controller;

import com.example.entity.MsgEntity;
import com.example.service.MessageProviderService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Resource
    private MessageProviderService providerService;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody MsgEntity entity) {
        try {
            providerService.sendMessage(entity);
            return ResponseEntity.ok("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
