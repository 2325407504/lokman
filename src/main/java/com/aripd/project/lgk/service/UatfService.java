package com.aripd.project.lgk.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Uatf;

public interface UatfService {

	Uatf findOne(Long id);

	List<Uatf> findAll();

	Uatf save(Uatf uatf);

	void delete(Long id);

	void delete(Uatf uatf);

	List<Uatf> findByForwardingId(Long id);

	ResultSet<Uatf> getRecords(Long forwarding_id, PagingCriteria criteria);

	void exportXLS(HttpServletResponse response);

}
