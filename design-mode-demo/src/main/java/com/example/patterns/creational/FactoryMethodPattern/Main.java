package com.example.patterns.creational.FactoryMethodPattern;

public class Main {
    public static void main(String[] args) {
        ConcreteCreator creator = new ConcreteCreator();
        Product product = creator.factoryMethod();
        product.operation();
    }
}
