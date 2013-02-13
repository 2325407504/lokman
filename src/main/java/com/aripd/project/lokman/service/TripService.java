package com.aripd.project.lokman.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lokman.domain.Trip;

public interface TripService {

	Trip getOne(Long id);

	List<Trip> getAll();

	Trip save(Trip trip);
	
	void saveOrUpdate(Trip trip);

	Trip delete(Long id);

	ResultSet<Trip> getRecords(PagingCriteria criteria);
	
	void exportXLS(HttpServletResponse response);

}
