package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
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

	ResultSet<Subcontractor> getRecords(PagingCriteria criteria);

}
