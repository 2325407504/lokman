package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Shift;

public interface ShiftService {

	Shift findOne(Long id);
	
	Shift findOneByCode(String code);
	
	List<Shift> findAll();
	
	Shift save(Shift shift);
	
	void delete(Long id);
	
	void delete(Shift shift);

	ResultSet<Shift> getRecords(PagingCriteria criteria);
	
}
