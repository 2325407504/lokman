package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

	Driver findOneByName(String name);
	
}
