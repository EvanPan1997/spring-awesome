package com.example;

import com.example.mapper.SystemUserMapper;
import com.example.entity.SystemUser;
import com.example.service.SystemUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SystemUserTest {
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private SystemUserMapper userMapper;

    @Test
    public void getUserByUserId() {
        try {
            String userId = "test1";
            SystemUser systemUser = systemUserService.getUserByUserId(userId);
            if (systemUser != null) {
                System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(systemUser));
            } else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertUser() {
        SystemUser systemUser = new SystemUser()
                .setId("test1")
                .setUserId("test1")
                .setUsername("test_user1")
                .setPassword("123")
                .setCreateUser("system")
                .setCreateTime("2024-09-27 11:20:00");
        userMapper.insertOrUpdate(systemUser);
    }
}
