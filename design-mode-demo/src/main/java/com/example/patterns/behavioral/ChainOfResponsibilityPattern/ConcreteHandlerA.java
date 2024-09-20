package com.example.patterns.behavioral.ChainOfResponsibilityPattern;

public class ConcreteHandlerA extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("A")) {
            System.out.println("Handler A handling request A");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
