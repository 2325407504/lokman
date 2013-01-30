package com.aripd.project.lokman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lokman.domain.Driver;
import com.aripd.project.lokman.domain.FMS;
import com.aripd.project.lokman.domain.Truck;

public interface FMSRepository extends JpaRepository<FMS, Long> {

	List<Driver> findByDriver(Driver driver);
	
	List<Truck> findByTruck(Truck truck);
	
}
