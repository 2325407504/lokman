package com.aripd.project.lokman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lokman.domain.Truck;

public interface TruckRepository extends JpaRepository<Truck, Long> {

	Truck findOneByPlate(String plate);

}
