package com.example.agent;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.ToolResponseMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AgentService {

    private final ChatModel chatModel;
    private final Map<String, ToolCallback> toolCallbackMap;

    public AgentService(ChatModel chatModel, List<ToolCallback> toolCallbacks) {
        this.chatModel = chatModel;
        this.toolCallbackMap = toolCallbacks.stream()
                .collect(Collectors.toMap(tc -> tc.getToolDefinition().name(), tc -> tc));
    }

    /**
     * 执行 Agent 任务，支持多轮工具调用
     *
     * @param userInput     用户输入
     * @param maxIterations 最大迭代次数
     * @return 最终回答
     */
    public String execute(String userInput, int maxIterations) {
        List<Message> messages = new ArrayList<>();
        messages.add(new UserMessage(userInput));

        int iteration = 0;
        while (iteration < maxIterations) {
            // 构建 Prompt，并显式传入工具选项（也可以使用 ChatModel 默认的，但为了灵活，这里显式指定）
            ToolCallingChatOptions toolOptions = ToolCallingChatOptions.builder()
                    .toolCallbacks(toolCallbackMap.values().stream().toList())
                    .build();

            Prompt prompt = new Prompt(messages, toolOptions);
            ChatResponse response = chatModel.call(prompt);

            // 获取模型返回的消息
            AssistantMessage assistantMessage = response.getResult().getOutput();
            messages.add(assistantMessage); // 保存模型回答

            // 检查是否有工具调用请求
            List<AssistantMessage.ToolCall> toolCalls = assistantMessage.getToolCalls();
            if (toolCalls.isEmpty()) {
                // 没有工具调用，返回最终答案
                return assistantMessage.getText();
            }

            // 执行所有工具调用
            for (AssistantMessage.ToolCall toolCall : toolCalls) {
                ToolCallback callback = toolCallbackMap.get(toolCall.name());
                if (callback == null) {
                    throw new IllegalStateException("未知工具: " + toolCall.name());
                }
                // 执行工具，传入参数 JSON 字符串
                String result = callback.call(toolCall.arguments());
                // 将工具执行结果作为消息加入对话
                messages.add(
                        ToolResponseMessage.builder()
                                .responses(List.of(new ToolResponseMessage.ToolResponse(toolCall.id(), toolCall.name(), result)))
                                .build()
                );
            }

            iteration++;
        }

        // 超过最大迭代次数，返回最后一条消息内容
        Message lastMsg = messages.getLast();
        return "已达到最大迭代次数，最后响应: " + lastMsg.getText();
    }
}