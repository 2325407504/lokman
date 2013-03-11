package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Quota;

public interface QuotaService {

	Quota findOne(Long id);
	
	List<Quota> findAll();
	
	Quota save(Quota quota);
	
	void delete(Long id);
	
	void delete(Quota quota);

}
