package com.example.patterns.behavioral.ChainOfResponsibilityPattern;

/**
 * 责任链模式
 * 优点:
 * 1.解耦-发送者和接收者解耦
 * 2.灵活性-可以动态地添加或修改处理者
 * */
public class Main {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler handlerC = new ConcreteHandlerC();
        handlerB.setNextHandler(handlerC);
        handlerA.setNextHandler(handlerB);

        handlerA.handleRequest("A");
        handlerA.handleRequest("B");
        handlerA.handleRequest("C");
        handlerA.handleRequest("D");
    }
}
