package com.aripd.project.lokman.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lokman.domain.Truck;
import com.aripd.project.lokman.repository.TruckRepository;
import com.aripd.project.lokman.service.TruckService;

@Service("truckService")
@Transactional
public class TruckServiceImpl implements TruckService {

	protected static Logger logger = Logger.getLogger(TruckServiceImpl.class);

	@Autowired
	private TruckRepository repository;

	public Truck getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public Truck getOneByPlate(String plate) {
		logger.debug("Retrieving person based on his plate");
		return repository.findOneByPlate(plate);
	}

	public List<Truck> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Truck save(Truck truck) {
		return repository.save(truck);
	}

	public Truck delete(Long id) {
		Truck truck = repository.findOne(id);
		repository.delete(id);
		return truck;
	}

}
