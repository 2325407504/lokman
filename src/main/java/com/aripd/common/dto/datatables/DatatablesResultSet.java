package com.aripd.common.dto.datatables;

import java.util.Collections;
import java.util.List;

import com.aripd.common.entity.BaseEntity;

public final class DatatablesResultSet<T extends BaseEntity> {

    private final List<T> rows;
    private final Integer totalDisplayRecords;
    private final Long totalRecords;

    public DatatablesResultSet(List<T> rows, Long totalRecords, Integer totalDisplayRecords) {
        this.rows = rows;
        this.totalRecords = totalRecords;
        this.totalDisplayRecords = totalDisplayRecords;
    }

    public List<T> getRows() {
        return Collections.unmodifiableList(rows);
    }

    public Integer getTotalDisplayRecords() {
        return totalDisplayRecords;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }
}
