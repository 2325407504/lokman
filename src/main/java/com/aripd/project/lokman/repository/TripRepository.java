package com.aripd.project.lokman.repository;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lokman.domain.Driver;
import com.aripd.project.lokman.domain.Trip;
import com.aripd.project.lokman.domain.Truck;

public interface TripRepository extends JpaRepository<Trip, Long> {

	List<Trip> findByDriver(Driver driver);
	
	List<Trip> findByTruck(Truck truck);
	
	List<Trip> findByPublishedAt(DateTime publishedAt);

}
