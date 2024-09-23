package com.example.patterns.behavioral.VisitorPattern;

public interface Visitor {
    void visit(ConcreteElementA elementA);
    void visit(ConcreteElementB elementB);
}
