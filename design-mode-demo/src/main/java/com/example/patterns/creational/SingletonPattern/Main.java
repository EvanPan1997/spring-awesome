package com.example.patterns.creational.SingletonPattern;

/**
 * 单例模式
 * 优点:
 * 1.控制实例数量-保证只有一个实例
 * 2.提供全局访问点-方便在全局范围访问该实例
 * */
public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1==singleton2);
    }
}
