package com.example.service;

import com.example.feign.CloudFeign;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class TestService {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CloudFeign cloudFeign;

    public String process(@PathVariable("msg") String msg) {
        log.info("client get request, msg: " + msg);
        String url = String.format("http://cloud-server/common/process/%s", msg);
        return this.restTemplate.getForObject(url, String.class);
    }

    public String process2(@PathVariable("msg") String msg) {
        log.info("client get request, msg: " + msg);
        return this.cloudFeign.process(msg);
    }
}
