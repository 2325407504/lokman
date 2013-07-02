package com.aripd.common.dto.datatables;

import java.util.Collections;
import java.util.List;

public final class DatatablesCriteria {

    private final String search;
    private final Integer displayStart;
    private final Integer displaySize;
    private final Integer pageNumber;
    private final List<DatatablesSortField> sortFields;

    public DatatablesCriteria(String search, Integer displayStart, Integer displaySize, Integer pageNumber, List<DatatablesSortField> sortFields) {
        this.search = search;
        this.displayStart = displayStart;
        this.displaySize = displaySize;
        this.pageNumber = pageNumber;
        this.sortFields = sortFields;
    }

    public String getSearch() {
        return search;
    }

    public Integer getDisplayStart() {
        return displayStart;
    }

    public Integer getDisplaySize() {
        return displaySize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public List<DatatablesSortField> getSortFields() {
        return Collections.unmodifiableList(sortFields);
    }
}