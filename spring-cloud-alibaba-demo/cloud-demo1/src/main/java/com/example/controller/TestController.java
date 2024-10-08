package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") String id) {
        return id;
    }
}
