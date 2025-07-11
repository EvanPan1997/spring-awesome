package com.example.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

@ToString
public enum OperatorType {
    EQ("EQ", "="),
    NOT_EQ("NOT_EQ", "<>");

    private final String code;
    private final String value;

    OperatorType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static OperatorType fromCode(String code) {
        return OperatorType.valueOf(code);
    }
}
