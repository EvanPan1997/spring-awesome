package com.example.service;

import com.example.entity.SystemUser;

import java.util.Map;

public interface LoginService {
    Map<String, String> login(SystemUser user);

    void logout();
}
