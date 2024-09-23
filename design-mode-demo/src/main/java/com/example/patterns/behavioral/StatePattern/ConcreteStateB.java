package com.example.patterns.behavioral.StatePattern;

public class ConcreteStateB implements State {
    @Override
    public void handle(Context context) {
        System.out.println("Handling state B");
        context.setState(new ConcreteStateA());  // 切换到状态A
    }
}
