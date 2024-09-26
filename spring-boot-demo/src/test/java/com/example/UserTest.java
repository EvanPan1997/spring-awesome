package com.example;

import com.example.model.User;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserTest {
    @Resource
    private UserService userService;

    @Test
    public void getUserByUserId() {
        try {
            String userId = "user";
            User user = userService.getUserByUserId(userId);
            if (user != null) {
                System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));
            } else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
