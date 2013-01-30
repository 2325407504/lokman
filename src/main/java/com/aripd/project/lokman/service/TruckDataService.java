package com.aripd.project.lokman.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lokman.domain.Truck;
import com.aripd.project.lokman.repository.TruckRepository;

@Service
public class TruckDataService {

	protected static Logger logger = Logger.getLogger(TruckDataService.class);

	@Autowired
	TruckRepository repository;

	public void init() {
		
		// add 100 more users without roles (To test pagination)
		List<Truck> trucks = new ArrayList<Truck>();
		Truck truck;
		for (int i = 0; i < 100; i++) {
			logger.debug("Adding a new record");
			truck = new Truck();
			truck.setPlate("34RV"+i);
			trucks.add(truck);
		}
		repository.save(trucks);
	}

}
