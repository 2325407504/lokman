package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Truck;

public interface TruckService {

	Truck findOne(Long id);
	
	Truck findOneByPlate(String plate);
	
	List<Truck> findAll();
	
	Truck save(Truck truck);
	
	void delete(Long id);

	void delete(Truck truck);

}
