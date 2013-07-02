package com.aripd.common.dto.autocomplete;

import java.util.Collections;
import java.util.List;

import com.aripd.common.entity.BaseEntity;

public final class AutocompleteResultSet<T extends BaseEntity> {

    private final List<T> rows;

    public AutocompleteResultSet(List<T> rows) {
        this.rows = rows;
    }

    public List<T> getRows() {
        return Collections.unmodifiableList(rows);
    }
}
