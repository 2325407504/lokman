package com.aripd.project.lgk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.repository.DriverRepository;

@Service
public class DriverDataService {

	@Autowired
	DriverRepository repository;

	public void init() {
		
		List<Driver> drivers = new ArrayList<Driver>();
		Driver driver;
		for (int i = 0; i < 100; i++) {
			driver = new Driver();
			driver.setFirstName("Firstname"+i);
			driver.setLastName("Lastname"+i);
			driver.setPhonenumber("Phone"+i);
			drivers.add(driver);
		}
		repository.save(drivers);
	}

}
