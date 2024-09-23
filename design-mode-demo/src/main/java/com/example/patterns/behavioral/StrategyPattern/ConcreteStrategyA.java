package com.example.patterns.behavioral.StrategyPattern;

public class ConcreteStrategyA implements Strategy {
    @Override
    public void execute() {
        System.out.println("Executing strategy A");
    }
}
