package com.aripd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.domain.FMS;
import com.aripd.repository.FMSRepository;

@Service("fmsService")
@Transactional
public class FMSService {

	protected static Logger logger4J = Logger.getLogger(FMSService.class);
	
	@Autowired
	private FMSRepository repository;
	
	public FMS getOne(Long id) {
		logger4J.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<FMS> getAll() {
		logger4J.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Boolean save(FMS fms) {
		FMS saved = repository.save(fms);
		if (saved == null)
			return false;

		logger4J.debug("Adding new person");
		return true;
	}
	
	public void delete(Long id) {
		repository.delete(repository.findOne(id));
	}

	public Boolean delete(FMS fms) {
		FMS existing = repository.findOne(fms.getId());
		if (existing == null)
			return false;

		repository.delete(existing);
		FMS deleted = repository.findOne(fms.getId());
		if (deleted != null)
			return false;

		logger4J.debug("Deleting existing person");
		return true;
	}

}
