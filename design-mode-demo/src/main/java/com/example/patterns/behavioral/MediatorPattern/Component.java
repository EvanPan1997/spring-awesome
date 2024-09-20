package com.example.patterns.behavioral.MediatorPattern;

public abstract class Component {
    protected Mediator mediator;

    public Component(Mediator mediator) {
        this.mediator=mediator;
    }

    public abstract void handleEvent(String event);
}
