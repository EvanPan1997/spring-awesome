package com.example.patterns.behavioral.CommandPattern;

/**
 * 命令模式
 * 优点:
 * 1.解耦-发送者和接收者解耦
 * 2.灵活性-可以动态地创建、撤销请求
 * */
public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.invoke();
    }
}
