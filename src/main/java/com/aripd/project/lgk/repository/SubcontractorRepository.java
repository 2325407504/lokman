package com.aripd.project.lgk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.domain.Subcontractor;

public interface SubcontractorRepository extends JpaRepository<Subcontractor, Long> {

	List<Subcontractor> findByRegion(Region region);
	
}
