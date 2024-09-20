package com.example.patterns.creational.BuilderPattern;

public class Product {
    private final String partA;
    private final String partB;

    public Product(String partA, String partB) {
        this.partA = partA;
        this.partB = partB;
    }

    @Override
    public String toString() {
        return "Product [partA=" + partA + ", partB=" + partB + "]";
    }
}
