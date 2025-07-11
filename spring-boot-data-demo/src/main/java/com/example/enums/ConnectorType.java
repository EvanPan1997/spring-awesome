package com.example.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

@ToString
public enum ConnectorType {
    AND("AND", "AND"),
    OR("OR", "");

    private final String code;
    @Getter
    private final String msg;

    ConnectorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static ConnectorType fromCode(String code) {
        return ConnectorType.valueOf(code);
    }
}
