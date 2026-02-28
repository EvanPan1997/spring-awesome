package com.example.agent;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

    @Resource
    private AgentService agentService;

    @PostMapping("/agent")
    public String chat(@RequestBody String message) {
        return agentService.execute(message, 5);
    }
}
