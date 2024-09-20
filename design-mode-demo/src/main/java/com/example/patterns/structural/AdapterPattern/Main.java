package com.example.patterns.structural.AdapterPattern;

/**
 * 适配器模式
 * 优点:
 * 1.接口兼容-使得接口不兼容的类可以协作
 * 2.复用性-可以复用现有的类
 * */
public class Main {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter(adaptee);
        adapter.operation();
    }
}
