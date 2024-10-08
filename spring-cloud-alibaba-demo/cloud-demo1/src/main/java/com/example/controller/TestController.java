package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/process/{msg}")
    public String process(@PathVariable("msg") String msg)
    {
        log.info("client get request, msg: " + msg);
        String url = String.format("http://cloud-server/common/process/%s", msg);
        return this.restTemplate.getForObject(url, String.class);
    }
}
