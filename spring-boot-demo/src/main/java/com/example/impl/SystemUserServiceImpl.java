package com.example.impl;

import com.example.mapper.SystemUserMapper;
import com.example.entity.SystemUser;
import com.example.feign.SystemUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private SystemUserMapper userMapper;

    @Override
    public void addUser(SystemUser systemUser) {

    }

    @Override
    public void updateUser(SystemUser systemUser) {

    }

    @Override
    public void deleteUser(SystemUser systemUser) {

    }

    @Override
    public SystemUser getUserByUserId(String userId) {
        return userMapper.getUserByUserId(userId);
    }
}
