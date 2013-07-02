package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Quota;

public interface QuotaService {

	Quota findOne(Long id);
	
	Quota findOneByCode(String code);

	List<Quota> findAll();
	
	Quota save(Quota quota);
	
	void delete(Long id);
	
	void delete(Quota quota);

	DatatablesResultSet<Quota> getRecords(DatatablesCriteria criteria);

}
