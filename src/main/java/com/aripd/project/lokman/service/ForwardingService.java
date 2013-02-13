package com.aripd.project.lokman.service;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lokman.domain.Forwarding;

public interface ForwardingService {

	Forwarding getOne(Long id);
	
	Forwarding save(Forwarding formData);

	Forwarding delete(Long id);

	ResultSet<Forwarding> getDataTables(PagingCriteria criteria);

	void exportXLS(HttpServletResponse response);
}
