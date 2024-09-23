package com.example.patterns.behavioral.TemplateMethodPattern;

public abstract class AbstractClass {
    public final void templateMethod() {
        step1();
        step2();
        step3();
    }

    public void step1() {
        System.out.println("Step 1");
    }

    protected abstract void step2();

    public void step3() {
        System.out.println("Step 2");
    }
}
