package com.example.patterns.behavioral.IteratorPattern;

import java.util.ArrayList;
import java.util.List;

public class Aggregate {
    private List<Object> items = new ArrayList<>();

    public void add(Object item) {
        items.add(item);
    }

    public Iterator iterator() {
        return new ConcreteIterator(items);
    }
}
