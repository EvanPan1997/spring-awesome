package com.example.patterns.behavioral.MementoPattern;

/**
 * 备忘录模式
 * 优点:
 * 1.状态恢复-可以在需要的时候恢复对象的状态
 * 2.封装性-不暴露对象的内部状态
 * */
public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State1");
        caretaker.saveMemento(originator.saveStateToMemento());

        originator.setState("State2");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(caretaker.retrieveMemento());
        System.out.println("Restored State: " + originator.getState());
    }
}
