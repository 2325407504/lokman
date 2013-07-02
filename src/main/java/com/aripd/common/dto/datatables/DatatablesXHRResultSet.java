package com.aripd.common.dto.datatables;

import com.aripd.common.dto.WebResultSet;
import java.util.Collections;
import java.util.List;

import com.aripd.common.entity.BaseEntity;

public class DatatablesXHRResultSet<T extends BaseEntity> implements WebResultSet<T> {

    private final Integer sEcho;
    private final Long iTotalRecords;
    private final Long iTotalDisplayRecords;
    private final List<T> aaData;

    public DatatablesXHRResultSet(DatatablesCriteria pc, DatatablesResultSet<T> rs) {
        this.sEcho = pc.getPageNumber();
        this.aaData = rs.getRows();
        this.iTotalRecords = rs.getTotalRecords();
        this.iTotalDisplayRecords = rs.getTotalRecords();
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public Long getiTotalRecords() {
        return iTotalRecords;
    }

    public Long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public List<T> getAaData() {
        return Collections.unmodifiableList(aaData);
    }
}