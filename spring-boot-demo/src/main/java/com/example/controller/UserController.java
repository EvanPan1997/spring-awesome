package com.example.controller;

import com.example.entity.SystemUser;
import com.example.feign.LoginService;
import com.example.utils.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult<Object> login(@RequestBody SystemUser systemUser) {
        try {
            Map<String, String> map = loginService.login(systemUser);
            return ResponseResult.success("登录成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.build(HttpStatus.UNAUTHORIZED.value(), "登录失败", e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseResult<String> logout() {
        loginService.logout();
        return ResponseResult.success("注销成功");
    }
}
