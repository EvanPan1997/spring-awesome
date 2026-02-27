package com.example.server;

import com.example.api.GreetingService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {
    @Resource
    private GreetingService greetingService;

    @GetMapping("/greet")
    String greet(@RequestParam String name) {
        return greetingService.sayHello(name);
    }
}
