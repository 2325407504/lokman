package com.aripd.project.lokman.service;

import java.util.List;

import org.joda.time.DateTime;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lokman.domain.Trip;

public interface TripService {

	public Trip getOne(Long id);
	
	public List<Trip> getAll();
	
	public Trip save(Trip trip);
	
	public Trip delete(Long id);

	public List<Trip> findByPublishedAt(DateTime publishedAt);

	public ResultSet<Trip> getRecords(PagingCriteria criteria);

}
