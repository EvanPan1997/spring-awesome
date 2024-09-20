package com.example.patterns.structural.FacadePattern;

public class Facade {
    private SubClassA subClassA;
    private SubClassB subClassB;

    public Facade() {
        this.subClassA = new SubClassA();
        this.subClassB = new SubClassB();
    }

    public void operation() {
        subClassA.operationA();
        subClassB.operationB();
    }
}
