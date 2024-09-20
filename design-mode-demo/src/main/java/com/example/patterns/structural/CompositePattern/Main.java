package com.example.patterns.structural.CompositePattern;

/**
 * 组合模式
 * 优点:
 * 1.一致性-对单个对象和组合对象一致性操作
 * 2.简化客户端代码-客户端代码可以统一处理叶子节点和容器节点
 * */
public class Main {
    public static void main(String[] args) {
        Composite root = new Composite();
        Leaf leaf1 = new Leaf("111");
        Leaf leaf2 = new Leaf("222");

        root.add(leaf1);
        root.add(leaf2);
        root.operation();

        root.remove(leaf1);
        root.operation();
    }
}
