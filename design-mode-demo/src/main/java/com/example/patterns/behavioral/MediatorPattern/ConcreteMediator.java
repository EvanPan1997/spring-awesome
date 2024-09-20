package com.example.patterns.behavioral.MediatorPattern;

public class ConcreteMediator implements Mediator {
    private ComponentA componentA;
    private ComponentB componentB;

    public void setComponentA(ComponentA componentA) {
        this.componentA = componentA;
    }

    public void setComponentB(ComponentB componentB) {
        this.componentB = componentB;
    }

    @Override
    public void notify(Component sender, String event) {
        if (sender == componentA) {
            componentA.handleEvent(event);
        } else if (sender == componentB) {
            componentB.handleEvent(event);
        }
    }
}
