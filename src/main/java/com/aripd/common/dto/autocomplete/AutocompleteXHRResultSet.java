package com.aripd.common.dto.autocomplete;

import com.aripd.common.dto.WebResultSet;
import java.util.Collections;
import java.util.List;

import com.aripd.common.entity.BaseEntity;

public class AutocompleteXHRResultSet<T extends BaseEntity> implements WebResultSet<T> {

    private final List<T> aaData;

    public AutocompleteXHRResultSet(AutocompleteResultSet<T> rs) {
        this.aaData = rs.getRows();
    }

    public List<T> getAaData() {
        return Collections.unmodifiableList(aaData);
    }
}