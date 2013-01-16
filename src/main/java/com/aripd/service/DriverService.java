package com.aripd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.domain.Driver;
import com.aripd.repository.DriverRepository;

@Service("driverService")
@Transactional
public class DriverService {

	protected static Logger logger4J = Logger.getLogger(DriverService.class);

	@Autowired
	private DriverRepository repository;
	
	public Driver getOne(Long id) {
		logger4J.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<Driver> getAll() {
		logger4J.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Boolean save(Driver driver) {
		Driver saved = repository.save(driver);
		if (saved == null)
			return false;

		logger4J.debug("Adding new person");
		return true;
	}
	
	public void delete(Long id) {
		repository.delete(repository.findOne(id));
	}

	public Boolean delete(Driver driver) {
		Driver existing = repository.findOne(driver.getId());
		if (existing == null)
			return false;

		repository.delete(existing);
		Driver deleted = repository.findOne(driver.getId());
		if (deleted != null)
			return false;

		logger4J.debug("Deleting existing person");
		return true;
	}

}
