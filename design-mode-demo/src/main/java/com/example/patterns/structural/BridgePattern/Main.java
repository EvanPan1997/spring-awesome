package com.example.patterns.structural.BridgePattern;

/**
 * 桥接模式
 * 优点:
 * 1.解耦-抽象和实现的解耦, 使得它们可以独立变化
 * 2.灵活性-可以独立地扩展抽象和实现
 * */
public class Main {
    public static void main(String[] args) {
        ConcreteImplementorA implementorA = new ConcreteImplementorA();
        RefinedAbstraction abstraction = new RefinedAbstraction(implementorA);
        abstraction.operation();

        ConcreteImplementorB implementorB = new ConcreteImplementorB();
        abstraction = new RefinedAbstraction(implementorB);
        abstraction.operation();
    }
}
