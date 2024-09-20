package com.example.patterns.creational.BuilderPattern;

/**
 * 建造者模式
 * 优点:
 * 1.解耦-将复杂对象的构建与表示解耦
 * 2.灵活性-可以根据需要创建不用表示的复杂对象
 * */
public class Main {
    public static void main(String[] args) {
        ConcreteBuilder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.getResult();
        System.out.println(product.toString());
    }
}
