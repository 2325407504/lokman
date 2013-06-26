package com.aripd.project.lgk.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Outgoing;

public interface OutgoingService {

	Outgoing findOne(Long id);

	List<Outgoing> findAll();

	Outgoing save(Outgoing outgoing);

	void delete(Long id);

	void delete(Outgoing outgoing);

	List<Outgoing> findByWaybillId(Long waybill_id);

	ResultSet<Outgoing> getRecords(Long waybill_id, PagingCriteria criteria);

	void exportXLS(HttpServletResponse response);

	void importXLSX(String fileName);

}
