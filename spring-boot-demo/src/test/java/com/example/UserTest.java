package com.example;

import com.example.mapper.UserMapper;
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
    @Resource
    private UserMapper userMapper;

    @Test
    public void getUserByUserId() {
        try {
            String userId = "test1";
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

    @Test
    public void insertUser() {
        User user = new User()
                .setId("test1")
                .setUserId("test1")
                .setUsername("test_user1")
                .setPassword("123")
                .setCreateUser("system")
                .setCreateTime("2024-09-27 11:20:00");
        userMapper.insertOrUpdate(user);
    }
}
