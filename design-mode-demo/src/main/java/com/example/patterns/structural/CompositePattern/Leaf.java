package com.example.patterns.structural.CompositePattern;

// 叶子节点
public class Leaf implements Component {
    private final String text;

    public Leaf(String text) {
        this.text = text;
    }

    @Override
    public void operation() {
        System.out.println(text);
    }
}
