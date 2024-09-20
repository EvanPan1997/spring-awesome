package com.example.patterns.structural.DecoratorPattern;

/**
 * 装饰器模式
 * 优点:
 * 1.灵活性-可以动态扩展对象的功能
 * 2.避免子类爆炸-通过装饰器而不是继承来扩展功能
 * */
public class Main {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Component decorator = new ConcreteDecorator(component);
        decorator.operation();
    }
}
