package com.example.controller;

import com.example.feign.CloudFeign;
import com.example.service.TestService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/process/{msg}")
    public String process(@PathVariable("msg") String msg) {
        return this.testService.process(msg);
    }

    @GetMapping("/process2/{msg}")
    public String process2(@PathVariable("msg") String msg) {
        return this.testService.process2(msg);
    }
}
