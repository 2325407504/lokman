package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.domain.Subcontractor;

public interface SubcontractorService {

	Subcontractor findOne(Long id);

	Subcontractor findOneByCode(String code);

	List<Subcontractor> findAll();

	List<Subcontractor> findByRegion(Region region);

	Subcontractor save(Subcontractor subcontractor);

	void delete(Long id);

	void delete(Subcontractor subcontractor);

	DatatablesResultSet<Subcontractor> getRecords(DatatablesCriteria criteria);

}
