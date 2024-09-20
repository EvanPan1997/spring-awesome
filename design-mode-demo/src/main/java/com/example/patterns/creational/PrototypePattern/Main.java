package com.example.patterns.creational.PrototypePattern;

/**
 * 原型模式
 * 优点:
 * 1.减少创建新对象的开销-通过复制现有对象创建新对象
 * 2.动态配置对象-可以在运行时配置对象状态
 * */
public class Main {
    public static void main(String[] args) {
        ConcretePrototype prototype = new ConcretePrototype("Hello");
        ConcretePrototype clonedPrototype = (ConcretePrototype) prototype.clone();
        System.out.println(clonedPrototype.getData());
        clonedPrototype.setData("World");
        System.out.println(prototype.getData());
        System.out.println(clonedPrototype.getData());
    }
}
