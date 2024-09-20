package com.example.patterns.behavioral.MediatorPattern;

/**
 * 中介者模式
 * 优点:
 * 1.降低耦合-将对象间的交互集中在中介者中
 * 2.易于维护-中介者可以集中处理复杂的交互逻辑
 * */
public class Main {
    public static void main(String[] args) {
        // 组件对象和中介者实现类的对象互相绑定
        ConcreteMediator mediator = new ConcreteMediator();
        ComponentA componentA = new ComponentA(mediator);
        ComponentB componentB = new ComponentB(mediator);
        mediator.setComponentA(componentA);
        mediator.setComponentB(componentB);
        // debug可以发现组件和中介者的对象相互依赖, 双方的成员变量都有对方的内存地址
        componentA.triggerEvent("Event A");
        componentB.handleEvent("Event B");
    }
}
