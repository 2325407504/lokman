package com.aripd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	
}
