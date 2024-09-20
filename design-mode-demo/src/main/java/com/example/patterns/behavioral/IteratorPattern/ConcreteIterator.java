package com.example.patterns.behavioral.IteratorPattern;

import java.util.List;
import java.util.Objects;

// 具体迭代器类
public class ConcreteIterator<T> implements Iterator<T> {
    private List<T> items;
    private int position;

    public ConcreteIterator(List<T> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.size();
    }

    @Override
    public T next() {
        return items.get(position++);
    }
}
