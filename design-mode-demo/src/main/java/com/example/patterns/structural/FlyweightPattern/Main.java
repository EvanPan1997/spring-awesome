package com.example.patterns.structural.FlyweightPattern;

/**
 * 享元模式
 * 优点:
 * 1.节省内存-通过共享来减少内存使用
 * 2.提高性能-减少对象创建和管理的开销
 * */
public class Main {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight("A");
        Flyweight flyweight2 = factory.getFlyweight("B");
        flyweight1.operation("1");
        flyweight2.operation("2");
    }
}
