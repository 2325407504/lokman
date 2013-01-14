package com.aripd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.domain.Truck;

public interface TruckRepository extends JpaRepository<Truck, Long> {
	
	Truck findOneByPlate(String plate);
}
