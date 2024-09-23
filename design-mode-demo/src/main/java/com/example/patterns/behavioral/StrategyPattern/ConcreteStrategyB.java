package com.example.patterns.behavioral.StrategyPattern;

public class ConcreteStrategyB implements Strategy {
    @Override
    public void execute() {
        System.out.println("Executing strategy B");
    }
}
