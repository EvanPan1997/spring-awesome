package com.example.server;

import com.example.api.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  private final GreetingService greetingService;

  GreetingController(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  @GetMapping("/api/greet")
  String greet(@RequestParam String name) {
    return greetingService.sayHello(name);
  }
}
