package com.example.patterns.behavioral.InterpreterPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 解释器模式
 * 优点:
 * 1.易于扩展-可以通过增加新的终结符和非终结符来扩展算法
 * 2.灵活性-可以定义复杂的语言规则
 * */
public class Main {
    public static void main(String[] args) {
        Expression expression = new PlusExpression(new NumberExpression(5), new NumberExpression(3));
        Map<String, Integer> context = new HashMap<>();
        int result = expression.interpret(context);
        System.out.println("Result: " + result);
    }
}
