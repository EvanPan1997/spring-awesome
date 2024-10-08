package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/process/{msg}")
    public String process(@PathVariable("msg") String msg) {
        String url = String.format("http://cloud-server/common/process/%s", msg);
        return this.restTemplate.getForObject(url, String.class);
    }
}
