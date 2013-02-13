package com.aripd.project.lokman.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lokman.domain.Subcontractor;
import com.aripd.project.lokman.repository.SubcontractorRepository;
import com.aripd.project.lokman.service.SubcontractorService;

@Service("subcontractorService")
@Transactional
public class SubcontractorServiceImpl implements SubcontractorService {

	protected static Logger logger = Logger.getLogger(SubcontractorServiceImpl.class);

	@Autowired
	private SubcontractorRepository repository;
	
	public Subcontractor getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<Subcontractor> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Subcontractor save(Subcontractor subcontractor) {
		return repository.save(subcontractor);
	}
	
	public Subcontractor delete(Long id) {
		Subcontractor subcontractor = repository.findOne(id);
		repository.delete(id);
		return subcontractor;
	}

}
