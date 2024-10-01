package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void commonTest() {
        redisTemplate.opsForValue().set("test_name", "111");
        System.out.println(redisTemplate.opsForValue().get("test_name"));
    }
}
