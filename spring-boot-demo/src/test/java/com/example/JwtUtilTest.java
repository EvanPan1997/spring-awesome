package com.example;

import com.example.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {
    @Test
    public void generateTest() {
        String userId = "test";
        String token = JwtUtils.generateToken(userId);
        System.out.println(token);
    }
}
