package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Subcontractor;

public interface SubcontractorService {

	Subcontractor findOne(Long id);

	List<Subcontractor> findAll();

	Subcontractor save(Subcontractor subcontractor);

	void delete(Long id);

	void delete(Subcontractor subcontractor);

}
