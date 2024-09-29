package com.example.controller;

import com.example.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/get")
    public ResponseResult<String> get() {
        return ResponseResult.success("Get request is processed");
    }

    @GetMapping("/get/{param}")
    public ResponseResult<String> get(@PathVariable("param") String param) {
        return ResponseResult.success("Get request is processed, param is " + param);
    }
}
