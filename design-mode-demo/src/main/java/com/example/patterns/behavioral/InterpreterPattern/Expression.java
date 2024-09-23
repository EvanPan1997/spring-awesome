package com.example.patterns.behavioral.InterpreterPattern;

import java.util.Map;

public interface Expression {
    int interpret(Map<String, Integer> context);
}
