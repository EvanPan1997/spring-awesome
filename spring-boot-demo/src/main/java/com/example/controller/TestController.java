package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/get")
    public ResponseEntity<String> get() {
        return ResponseEntity.status(200).body("Get request is processed");
    }

    @GetMapping("/get/{param}")
    public ResponseEntity<String> get(@PathVariable("param") String param) {
        return ResponseEntity.ok("Get request is processed, param is " + param);
    }
}
