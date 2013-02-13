package com.aripd.project.lokman.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lokman.domain.Subcontractor;
import com.aripd.project.lokman.repository.SubcontractorRepository;

@Service
public class SubcontractorDataService {

	protected static Logger logger = Logger.getLogger(SubcontractorDataService.class);

	@Autowired
	SubcontractorRepository repository;

	public void init() {
		
		List<Subcontractor> subcontractors = new ArrayList<Subcontractor>();
		for (int i = 0; i < 10; i++) {
			logger.debug("Adding a new record");
			Subcontractor subcontractor = new Subcontractor();
			subcontractor.setName("Subcontractor"+i);
			subcontractors.add(subcontractor);
		}
		repository.save(subcontractors);
	}

}
