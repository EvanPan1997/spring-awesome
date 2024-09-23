package com.example.patterns.behavioral.VisitorPattern;

/**
 * 访问者模式
 * 优点:
 * 1.扩展性-可以在不改变对象结构的情况下增加新的操作
 * 2.操作集中-操作可以被集中在访问者中, 使得相关操作更易于维护
 * */
public class Main {
    public static void main(String[] args) {
        ObjectStructure structure = new ObjectStructure();
        structure.addElement(new ConcreteElementA());
        structure.addElement(new ConcreteElementB());

        Visitor visitorA = new ConcreteVisitorA();
        structure.accept(visitorA);

        Visitor visitorB = new ConcreteVisitorB();
        structure.accept(visitorB);
    }
}
