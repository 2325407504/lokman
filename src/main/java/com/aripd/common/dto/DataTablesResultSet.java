package com.aripd.common.dto;

import java.util.Collections;
import java.util.List;

import com.aripd.common.entity.BaseEntity;

public class DataTablesResultSet<T extends BaseEntity> implements
		WebResultSet<T> {
	private final Integer sEcho;
	private final Long iTotalRecords;
	private final Long iTotalDisplayRecords;
	private final List<T> aaData;

	public DataTablesResultSet(PagingCriteria pc, ResultSet<T> rs) {
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