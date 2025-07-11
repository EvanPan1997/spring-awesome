package com.example.controller;

import cn.hutool.json.JSONObject;
import com.example.req.EnumDemoReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/enumDemo")
    public ResponseEntity<String> enumDemo(@RequestBody EnumDemoReq req) {
        try {
            System.out.println(req.getType().toString());
            return ResponseEntity.ok(new JSONObject(req).toStringPretty());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
