package com.example.patterns.behavioral.StatePattern;

/**
 * 状态模式
 * 优点:
 * 1.状态独立-每个状态都有自己的行为
 * 2.易于扩展-可以增加新的状态而不改变现有代码
 * */
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.request();
        context.request();
    }
}
