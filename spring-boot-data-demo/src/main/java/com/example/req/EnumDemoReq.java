package com.example.req;

import com.example.enums.ConnectorType;
import com.example.enums.OperatorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class EnumDemoReq {
    private ConnectorType type;

    private List<Param> params;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Accessors(chain = true)
    public static class Param {
        private ConnectorType type;
        private String field;
        private OperatorType operator;
        private String value;
    }
}
