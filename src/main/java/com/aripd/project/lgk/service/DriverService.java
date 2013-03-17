package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Driver;

public interface DriverService {

	Driver findOne(Long id);
	
	List<Driver> findAll();
	
	Driver save(Driver driver);
	
	void delete(Long id);
	
	void delete(Driver driver);

	ResultSet<Driver> getRecords(PagingCriteria criteria);
	
}
