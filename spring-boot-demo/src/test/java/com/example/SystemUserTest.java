package com.example;

import com.example.mapper.SystemUserMapper;
import com.example.entity.SystemUser;
import com.example.feign.SystemUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SystemUserTest {
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private SystemUserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

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

    @Test
    public void encodePassword() {
        String password = "123";
        String encoded = passwordEncoder.encode(password);
        System.out.println(encoded);
    }
}
