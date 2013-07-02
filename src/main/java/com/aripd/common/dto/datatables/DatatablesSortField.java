package com.aripd.common.dto.datatables;

public final class DatatablesSortField {

    private final String field;
    private final DatatablesSortDirection direction;

    public DatatablesSortField(String field, DatatablesSortDirection direction) {
        this.field = field;
        this.direction = direction;
    }

    public DatatablesSortField(String field, String direction) {
        this.field = field;
        this.direction = DatatablesSortDirection.valueOfCaseInsensitive(direction);
    }

    public String getField() {
        return field;
    }

    public DatatablesSortDirection getDirection() {
        return direction;
    }
}