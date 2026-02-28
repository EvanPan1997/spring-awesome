package com.example.model;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModelTest {

    @Resource
    private ChatModel chatModel;

    @Test
    public void printModelInfo() {
        // 查看 OllamaChatModel 的默认选项
        ChatOptions defaultOptions = chatModel.getDefaultOptions();

        System.out.println("默认模型: " + defaultOptions.getModel());
        System.out.println("温度: " + defaultOptions.getTemperature());
        System.out.println("其他参数: " + defaultOptions);
    }
}
