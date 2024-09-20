package com.example.patterns.creational.BuilderPattern;

public interface Builder {
    void buildPartA();
    void buildPartB();
    Product getResult();
}
