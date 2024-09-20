package com.example.patterns.behavioral.MediatorPattern;

public class ComponentA extends Component {

    public ComponentA(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void handleEvent(String event) {
        System.out.println("ComponentA handling event: " + event);
    }

    public void triggerEvent(String event) {
        mediator.notify(this, event);
    }
}
