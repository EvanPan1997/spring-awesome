package com.example.patterns.behavioral.TemplateMethodPattern;

/**
 * 模板方法模式
 * 优点:
 * 1.复用性-将公共算法逻辑放在父类中
 * 2.灵活性-子类可以改变某些步骤的实现而不改变算法结构
 * */
public class Main {
    public static void main(String[] args) {
        AbstractClass concreteClassA = new ConcreteClassA();
        concreteClassA.templateMethod();

        AbstractClass concreteClassB = new ConcreteClassB();
        concreteClassB.templateMethod();
    }
}
