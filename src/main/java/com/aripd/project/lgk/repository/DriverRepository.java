package com.aripd.project.lgk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.domain.Region;

public interface DriverRepository extends JpaRepository<Driver, Long> {

	Driver findOneByCode(String code);

	List<Driver> findByRegion(Region region);
	
}
