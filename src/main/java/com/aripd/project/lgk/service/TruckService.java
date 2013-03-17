package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.domain.Truck;

public interface TruckService {

	Truck findOne(Long id);
	
	Truck findOneByPlate(String plate);
	
	List<Truck> findAll();
	
	List<Truck> findByRegion(Region region);

	Truck save(Truck truck);
	
	void delete(Long id);

	void delete(Truck truck);

	ResultSet<Truck> getRecords(PagingCriteria criteria);

}
