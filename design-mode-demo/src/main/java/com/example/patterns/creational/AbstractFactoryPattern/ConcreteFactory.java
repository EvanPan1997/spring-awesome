package com.example.patterns.creational.AbstractFactoryPattern;

public class ConcreteFactory implements AbstractFactory {
    @Override
    public Product createProduct() {
        return new ConcreteProduct();
    }
}
