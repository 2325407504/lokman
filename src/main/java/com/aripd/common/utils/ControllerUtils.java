package com.aripd.common.utils;

import com.aripd.common.dto.DataTablesResultSet;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.entity.BaseEntity;

public class ControllerUtils {

	public static <T extends BaseEntity> WebResultSet<T> getWebResultSet(PagingCriteria pc, ResultSet<T> rs) {
		return new DataTablesResultSet<T>(pc, rs);
	}

}
