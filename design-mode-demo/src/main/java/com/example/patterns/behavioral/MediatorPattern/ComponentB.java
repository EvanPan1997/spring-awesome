package com.example.patterns.behavioral.MediatorPattern;

public class ComponentB extends Component {
    public ComponentB(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void handleEvent(String event) {
        System.out.println("ComponentB handling event: " + event);
    }
}
