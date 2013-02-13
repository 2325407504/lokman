package com.aripd.project.lokman.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lokman.domain.Uatf;

public interface UatfService {

	Uatf getOne(Long id);

	List<Uatf> getAll();

	Uatf save(Uatf uatf);

	Uatf delete(Long id);

	List<Uatf> getByForwardingId(Long id);

	ResultSet<Uatf> getDataTables(Long forwarding_id, PagingCriteria criteria);

	void exportXLS(HttpServletResponse response);

}
