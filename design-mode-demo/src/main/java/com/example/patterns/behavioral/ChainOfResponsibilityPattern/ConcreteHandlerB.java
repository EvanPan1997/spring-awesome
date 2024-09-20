package com.example.patterns.behavioral.ChainOfResponsibilityPattern;

public class ConcreteHandlerB extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("B")) {
            System.out.println("Handler B handling request B");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
