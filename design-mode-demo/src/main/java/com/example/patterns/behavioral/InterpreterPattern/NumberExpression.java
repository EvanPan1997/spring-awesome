package com.example.patterns.behavioral.InterpreterPattern;

import java.util.Map;

/** 终结符表达式 */
public class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return number;
    }
}
