package com.example.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

// 请求处理监听器(不适用于系统日志)
@Component
public class RequestHandledListener implements ApplicationListener<RequestHandledEvent> {

    @Override
    public void onApplicationEvent(RequestHandledEvent event) {
        System.out.println("Request handled");
    }
}
