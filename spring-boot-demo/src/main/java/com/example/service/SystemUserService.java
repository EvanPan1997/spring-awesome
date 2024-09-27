package com.example.service;

import com.example.entity.SystemUser;

public interface SystemUserService {
    void addUser(SystemUser systemUser);
    void updateUser(SystemUser systemUser);
    void deleteUser(SystemUser systemUser);

    SystemUser getUserByUserId(String userId);
}
