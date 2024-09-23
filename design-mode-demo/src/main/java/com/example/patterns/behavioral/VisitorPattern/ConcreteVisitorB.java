package com.example.patterns.behavioral.VisitorPattern;

public class ConcreteVisitorB implements Visitor {
    @Override
    public void visit(ConcreteElementA elementA) {
        System.out.println("Visitor B visiting Element A");
    }

    @Override
    public void visit(ConcreteElementB elementB) {
        System.out.println("Visitor B visiting Element B");
    }
}
