package com.aripd.project.lgk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Subcontractor;
import com.aripd.project.lgk.repository.SubcontractorRepository;
import com.aripd.project.lgk.service.SubcontractorService;

@Service("subcontractorService")
@Transactional
public class SubcontractorServiceImpl implements SubcontractorService {

	@Autowired
	private SubcontractorRepository repository;
	
	public Subcontractor findOne(Long id) {
		return repository.findOne(id);
	}

	public List<Subcontractor> findAll() {
		return repository.findAll();
	}

	public Subcontractor save(Subcontractor subcontractor) {
		return repository.save(subcontractor);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}

	public void delete(Subcontractor subcontractor) {
		repository.delete(subcontractor);
	}

}
