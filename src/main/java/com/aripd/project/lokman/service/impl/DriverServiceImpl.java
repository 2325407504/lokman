package com.aripd.project.lokman.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lokman.domain.Driver;
import com.aripd.project.lokman.repository.DriverRepository;
import com.aripd.project.lokman.service.DriverService;

@Service("driverService")
@Transactional
public class DriverServiceImpl implements DriverService {

	protected static Logger logger = Logger.getLogger(DriverServiceImpl.class);

	@Autowired
	private DriverRepository repository;
	
	public Driver getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<Driver> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Driver save(Driver driver) {
		return repository.save(driver);
	}
	
	public Driver delete(Long id) {
		Driver driver = repository.findOne(id);
		repository.delete(id);
		return driver;
	}

}
