package com.example.patterns.structural.AdapterPattern;

public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee=adaptee;
    }
    @Override
    public void operation() {
        adaptee.specificOperation();
    }
}
