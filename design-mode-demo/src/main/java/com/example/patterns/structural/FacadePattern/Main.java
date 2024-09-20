package com.example.patterns.structural.FacadePattern;

/**
 * 外观模式
 * 优点:
 * 1.简化使用-提供简单的接口来访问复杂的子系统
 * 2.解耦-将客户端与子系统解耦
 * */
public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.operation();
    }
}
