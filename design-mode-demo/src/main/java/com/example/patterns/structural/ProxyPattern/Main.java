package com.example.patterns.structural.ProxyPattern;

/**
 * 代理模式
 * 优点:
 * 1.控制访问-可以在代理中实现对真实对象的访问
 * 2.增强功能-在代理中增加额外的功能, 如延迟加载
 * */
public class Main {
    public static void main(String[] args) {
        Subject proxy = new Proxy();
        proxy.request();
    }
}
