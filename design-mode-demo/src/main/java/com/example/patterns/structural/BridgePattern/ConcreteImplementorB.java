package com.example.patterns.structural.BridgePattern;

public class ConcreteImplementorB implements Implementor {
    @Override
    public void operation() {
        System.out.println("ConcreteImplementorB operation");
    }
}
