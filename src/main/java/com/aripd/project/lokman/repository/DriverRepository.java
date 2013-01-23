package com.aripd.project.lokman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lokman.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	
}
