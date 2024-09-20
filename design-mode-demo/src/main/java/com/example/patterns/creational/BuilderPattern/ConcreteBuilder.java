package com.example.patterns.creational.BuilderPattern;

public class ConcreteBuilder implements Builder {
    private String partA;
    private String partB;

    @Override
    public void buildPartA() {
        partA = "Part A";
    }

    @Override
    public void buildPartB() {
        partB = "Part B";
    }

    @Override
    public Product getResult() {
        return new Product(partA, partB);
    }
}
