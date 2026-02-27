package com.example.controller;

import com.example.api.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetController {

    @DubboReference(url = "${dubbo.reference.url.greeting}")
    private GreetingService greetingService;

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        String dubboResp = this.greetingService.sayHello("Evan");
        return ResponseEntity.ok(dubboResp);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
