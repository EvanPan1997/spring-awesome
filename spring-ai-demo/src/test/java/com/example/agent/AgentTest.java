package com.example.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AgentTest {

    @Resource
    private AgentService agentService;

    @Test
    public void execute() {
        String result = agentService.execute("给我查一下上海3月的天气", 5);
        System.out.println(result);
    }
}
