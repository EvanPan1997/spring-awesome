package com.example.patterns.behavioral.ObserverPattern;

/**
 * 观察者模式
 * 优点:
 * 1.解耦-观察者和被观察者之间的解耦
 * 2.动态更新-自动更新所有观察者
 * */
public class Main {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver("Observer1");
        Observer observer2 = new ConcreteObserver("Observer2");

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.notifyObservers("Hello Observers!");
    }
}
