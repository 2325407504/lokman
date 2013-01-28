package com.aripd.common.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lokman.domain.Driver;
import com.aripd.project.lokman.repository.DriverRepository;

@Service
public class DriverDataService {

	protected static Logger logger4J = Logger.getLogger(DriverDataService.class);

	@Autowired
	DriverRepository repository;

	public void init() {
		
		// add 100 more users without roles (To test pagination)
		List<Driver> drivers = new ArrayList<Driver>();
		Driver driver;
		for (int i = 0; i < 100; i++) {
			logger4J.debug("Adding a new record");
			driver = new Driver();
			driver.setFirstName("Firstname"+i);
			driver.setLastName("Lastname"+i);
			drivers.add(driver);
		}
		repository.save(drivers);
	}

}
