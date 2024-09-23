package com.example.patterns.behavioral.StatePattern;

public class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("Handling state A");
        context.setState(new ConcreteStateB());
    }
}
