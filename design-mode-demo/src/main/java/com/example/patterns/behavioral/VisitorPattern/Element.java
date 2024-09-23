package com.example.patterns.behavioral.VisitorPattern;

public interface Element {
    void accept(Visitor visitor);
}
