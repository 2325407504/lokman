package com.aripd.common.dto.datatables;

import org.apache.log4j.Logger;

public enum DatatablesSortDirection {

    ASC("asc"), DESC("desc");
    private String direction;
    protected static Logger logger = Logger.getLogger(DatatablesSortDirection.class);

    private DatatablesSortDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public static DatatablesSortDirection valueOfCaseInsensitive(String value) {
        String valueUpper = value.toUpperCase();
        DatatablesSortDirection sd = DatatablesSortDirection.valueOf(valueUpper);
        logger.debug("SortDirection converted " + value + " to " + sd);
        return sd;
    }
}