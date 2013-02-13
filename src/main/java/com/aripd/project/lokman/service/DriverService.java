package com.aripd.project.lokman.service;

import java.util.List;

import com.aripd.project.lokman.domain.Driver;

public interface DriverService {

	Driver getOne(Long id);
	
	List<Driver> getAll();
	
	Driver save(Driver driver);
	
	Driver delete(Long id);
	
}
