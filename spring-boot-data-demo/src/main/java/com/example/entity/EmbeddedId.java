package com.example.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class EmbeddedId implements Serializable {
    private String versionId;
    private Long tableId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmbeddedId that = (EmbeddedId) o;
        return Objects.equals(versionId, that.versionId) && Objects.equals(tableId, that.tableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionId, tableId);
    }
}
