package com.aripd.project.lokman.service;

import java.util.List;

import com.aripd.project.lokman.domain.FMS;

public interface FMSService {

	public FMS getOne(Long id);
	
	public List<FMS> getAll();
	
	public FMS save(FMS fms);
	
	public FMS delete(Long id);

}
