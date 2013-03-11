package com.aripd.project.lgk.service;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Trip;

public interface TripService {

	Trip findOne(Long id);

	Trip findOneByAccountAndId(Account account, Long id);
	
	List<Trip> findAll();

	Trip save(Trip trip);
	
	void delete(Long id);

	void delete(Trip trip);
	
	ResultSet<Trip> getRecords(PagingCriteria criteria);
	
	ResultSet<Trip> getRecords(Principal principal, PagingCriteria criteria);
	
	void exportXLS(HttpServletResponse response);

}
