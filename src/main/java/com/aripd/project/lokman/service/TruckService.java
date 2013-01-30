package com.aripd.project.lokman.service;

import java.util.List;

import com.aripd.project.lokman.domain.Truck;

public interface TruckService {

	public Truck getOne(Long id);
	
	public Truck getOneByPlate(String plate);
	
	public List<Truck> getAll();
	
	public Truck save(Truck truck);
	
	public Truck delete(Long id);

}
