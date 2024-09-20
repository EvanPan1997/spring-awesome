package com.example.patterns.behavioral.ChainOfResponsibilityPattern;

public class ConcreteHandlerC extends Handler{
    @Override
    public void handleRequest(String request) {
        if (request.equals("C")) {
            System.out.println("Handler C handling request C");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
