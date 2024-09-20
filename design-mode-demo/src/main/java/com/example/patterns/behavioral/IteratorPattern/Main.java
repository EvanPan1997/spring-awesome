package com.example.patterns.behavioral.IteratorPattern;

import java.util.ArrayList;

/**
 * 迭代器模式
 * 优点:
 * 1.简化访问-提供统一的访问方式
 * 2.解耦-容器和迭代器解耦
 * */
public class Main {
    public static void main(String[] args) {
        Aggregate aggregate = new Aggregate();
        aggregate.add("1");
        aggregate.add("2");
        aggregate.add("3");

        Iterator iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        ArrayList<Object> objects = new ArrayList<>();
    }
}
