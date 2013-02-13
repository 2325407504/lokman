package com.aripd.project.lokman.service;

import java.util.List;

import com.aripd.project.lokman.domain.Subcontractor;

public interface SubcontractorService {

	List<Subcontractor> getAll();

	Subcontractor getOne(Long id);

	Subcontractor save(Subcontractor subcontractor);

	Subcontractor delete(Long id);

}
