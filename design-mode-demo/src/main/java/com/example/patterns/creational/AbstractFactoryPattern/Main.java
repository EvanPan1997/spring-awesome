package com.example.patterns.creational.AbstractFactoryPattern;

/**
 * 抽象工厂模式
 * 优点:
 * 1.一致性-确保创建的一系列对象具有一致性
 * 2.扩展性-易于扩展产品系列, 而不影响现有代码
 * */
public class Main {
    public static void main(String[] args) {
        ConcreteFactory factory = new ConcreteFactory();
        Product product = factory.createProduct();
        product.operation();
    }
}
