package com.example.patterns.behavioral.VisitorPattern;

public class ConcreteVisitorA implements Visitor {
    @Override
    public void visit(ConcreteElementA elementA) {
        System.out.println("Visitor A visiting Element A");
    }

    @Override
    public void visit(ConcreteElementB elementB) {
        System.out.println("Visitor A visiting Element B");
    }
}
