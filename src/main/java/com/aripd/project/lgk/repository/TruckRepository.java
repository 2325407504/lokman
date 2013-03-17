package com.aripd.project.lgk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.domain.Truck;

public interface TruckRepository extends JpaRepository<Truck, Long> {

	Truck findOneByPlate(String plate);

	List<Truck> findByRegion(Region region);

}
