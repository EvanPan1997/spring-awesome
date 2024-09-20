package com.example.patterns.behavioral.IteratorPattern;

// 迭代器接口
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
