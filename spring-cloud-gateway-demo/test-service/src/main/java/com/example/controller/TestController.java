package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class TestController {

    @GetMapping("/get")
    public String get() {
        return "test";
    }

    @PostMapping("/post")
    public String post(@RequestBody String body) {
        return body;
    }
}
