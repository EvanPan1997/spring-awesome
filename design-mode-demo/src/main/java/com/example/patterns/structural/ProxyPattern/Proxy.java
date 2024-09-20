package com.example.patterns.structural.ProxyPattern;

public class Proxy implements Subject {
    private RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.request();  // 代理控制对真实主题的访问
    }
}
