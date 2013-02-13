package com.aripd.project.lokman.service;

import java.util.List;

import com.aripd.project.lokman.domain.Quota;

public interface QuotaService {

	Quota getOne(Long id);
	
	List<Quota> getAll();
	
	Quota save(Quota quota);
	
	Quota delete(Long id);

}
