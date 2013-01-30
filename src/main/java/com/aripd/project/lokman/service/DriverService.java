package com.aripd.project.lokman.service;

import java.util.List;

import com.aripd.project.lokman.domain.Driver;

public interface DriverService {

	public Driver getOne(Long id);
	
	public List<Driver> getAll();
	
	public Driver save(Driver driver);
	
	public Driver delete(Long id);
	
}
