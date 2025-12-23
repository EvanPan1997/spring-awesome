package com.example.controller;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/value/get/{key}")
    public String getForValue(@PathVariable String key) {
        return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
    }

    @PostMapping("/value/set/{key}/{value}")
    public void setForValue(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value, 30, TimeUnit.MINUTES);
    }
}
