package com.aripd.project.lgk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.repository.DriverRepository;
import com.aripd.project.lgk.service.DriverService;

@Service("driverService")
@Transactional
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository repository;
	
	public Driver findOne(Long id) {
		return repository.findOne(id);
	}

	public List<Driver> findAll() {
		return repository.findAll();
	}

	public Driver save(Driver driver) {
		return repository.save(driver);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}

	public void delete(Driver driver) {
		repository.delete(driver);
	}

}
