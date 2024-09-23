package com.example.patterns.behavioral.StrategyPattern;

/**
 * 策略模式
 * 优点:
 * 1.灵活性-可以动态选择算法
 * 2.易于扩展-可以新增策略而不影响现有代码
 * */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new ConcreteStrategyA());
        context.executeStrategy();

        context.setStrategy(new ConcreteStrategyB());
        context.executeStrategy();
    }
}
