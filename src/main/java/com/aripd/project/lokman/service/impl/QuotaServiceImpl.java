package com.aripd.project.lokman.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lokman.domain.Quota;
import com.aripd.project.lokman.repository.QuotaRepository;
import com.aripd.project.lokman.service.QuotaService;

@Service("quotaService")
@Transactional
public class QuotaServiceImpl implements QuotaService {

	protected static Logger logger = Logger.getLogger(QuotaServiceImpl.class);

	@Autowired
	private QuotaRepository repository;
	
	public Quota getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<Quota> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Quota save(Quota quota) {
		return repository.save(quota);
	}
	
	public Quota delete(Long id) {
		Quota quota = repository.findOne(id);
		repository.delete(id);
		return quota;
	}

}
