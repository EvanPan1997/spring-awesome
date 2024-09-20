package com.example.patterns.creational.AbstractFactoryPattern;

public class ConcreteProduct implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProduct operation");
    }
}
