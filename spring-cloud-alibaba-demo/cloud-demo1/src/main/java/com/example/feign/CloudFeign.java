package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cloud-server",  path = "/common")
public interface CloudFeign {

    @GetMapping("/process/{msg}")
    String process(@PathVariable("msg") String msg);

}
