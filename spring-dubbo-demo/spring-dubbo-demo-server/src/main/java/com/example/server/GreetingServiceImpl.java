package com.example.server;

import com.example.api.GreetingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
@DubboService(interfaceClass = com.example.api.GreetingService.class)
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
