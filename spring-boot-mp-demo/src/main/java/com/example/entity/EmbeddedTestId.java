package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class EmbeddedTestId implements Serializable {
    private String versionId;
    private String tableId;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        EmbeddedTestId that = (EmbeddedTestId) object;
        return Objects.equals(versionId, that.versionId) && Objects.equals(tableId, that.tableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionId, tableId);
    }
}
