package com.aripd.project.lgk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.repository.TruckRepository;
import com.aripd.project.lgk.service.TruckService;

@Service("truckService")
@Transactional
public class TruckServiceImpl implements TruckService {

	@Autowired
	private TruckRepository repository;

	public Truck findOne(Long id) {
		return repository.findOne(id);
	}

	public Truck findOneByPlate(String plate) {
		return repository.findOneByPlate(plate);
	}

	public List<Truck> findAll() {
		return repository.findAll();
	}

	public Truck save(Truck truck) {
		return repository.save(truck);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public void delete(Truck truck) {
		repository.delete(truck);
	}

}
