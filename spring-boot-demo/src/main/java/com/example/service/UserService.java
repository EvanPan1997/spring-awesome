package com.example.service;

import com.example.model.User;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);

    User getUserByUserId(String userId);
}
