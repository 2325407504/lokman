package com.aripd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.domain.Truck;
import com.aripd.repository.TruckRepository;

@Service("truckService")
@Transactional
public class TruckService {

	protected static Logger logger4J = Logger.getLogger(TruckService.class);

	@Autowired
	private TruckRepository repository;
	
	public Truck get(Long id) {
		logger4J.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public Truck getOneByPlate(String plate) {
		logger4J.debug("Retrieving person based on his plate");
		return repository.findOneByPlate(plate);
	}

	public List<Truck> getAll() {
		logger4J.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Boolean save(Truck truck) {
		Truck existing = repository.findOneByPlate(truck.getPlate());
		if (existing != null)
			return false;

		Truck saved = repository.save(truck);
		if (saved == null)
			return false;

		logger4J.debug("Adding new person");
		return true;
	}
	
	public Boolean create(Truck truck) {
		Truck existing = repository.findOneByPlate(truck.getPlate());
		if (existing != null)
			return false;

		Truck saved = repository.save(truck);
		if (saved == null)
			return false;

		logger4J.debug("Adding new person");
		return true;
	}

	public Boolean update(Truck truck) {
		Truck existing = repository.findOneByPlate(truck.getPlate());
		if (existing == null)
			return false;

		// Only firstName, lastName, and role fields are updatable
		existing.setPlate(truck.getPlate());

		Truck saved = repository.save(existing);
		if (saved == null)
			return false;

		logger4J.debug("Editing existing person");
		return true;
	}

	public void delete(Long id) {
		repository.delete(repository.findOne(id));
	}

	public Boolean delete(Truck truck) {
		Truck existing = repository.findOne(truck.getId());
		if (existing == null)
			return false;

		repository.delete(existing);
		Truck deleted = repository.findOne(truck.getId());
		if (deleted != null)
			return false;

		logger4J.debug("Deleting existing person");
		return true;
	}

}
