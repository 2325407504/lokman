package com.aripd.project.lgk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.project.lgk.domain.Quota;
import com.aripd.project.lgk.repository.QuotaRepository;

@Service
public class QuotaDataService {

	protected static Logger logger = Logger.getLogger(QuotaDataService.class);

	@Autowired
	QuotaRepository repository;

	public void init() {
		
		List<Quota> quotas = new ArrayList<Quota>();
		
		Quota quota1 = new Quota();
		quota1.setName("LASDER");
		quotas.add(quota1);
		
		Quota quota2 = new Quota();
		quota2.setName("Çimento Fabrikası");
		quotas.add(quota2);
		
		repository.save(quotas);
	}

}
