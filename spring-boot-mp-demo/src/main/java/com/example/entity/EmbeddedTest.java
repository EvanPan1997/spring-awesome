package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName
public class EmbeddedTest {
    @TableId(type = IdType.NONE)
    private EmbeddedTestId id;
//    private String versionId;
//    private Long tableId;
    private String tableSchema;
    private String tableName;
}
