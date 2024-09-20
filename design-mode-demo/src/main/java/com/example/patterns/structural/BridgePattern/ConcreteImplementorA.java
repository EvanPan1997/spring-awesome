package com.example.patterns.structural.BridgePattern;

public class ConcreteImplementorA implements Implementor {
    @Override
    public void operation() {
        System.out.println("ConcreteImplementorA operation");
    }
}
