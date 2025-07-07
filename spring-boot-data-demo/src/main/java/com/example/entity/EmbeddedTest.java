package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Entity
@Table(name = "embedded_test",
        indexes = {@Index(name = "unique_idx", columnList = "version_id, table_schema, table_name", unique = true)})
@IdClass(EmbeddedId.class)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EmbeddedTest {
    @Id
    private String versionId;
    @Id
    private Long tableId;

    @Column
    private String tableSchema;

    @Column
    private String tableName;

    @Column(name = "value_a")
    private String valueA;

    @Column(name = "value_b")
    private String valueB;

    @Column(name = "value_c")
    private String valueC;
}
