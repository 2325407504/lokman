package com.aripd.project.lokman.service;

import java.util.List;

import com.aripd.project.lokman.domain.Shift;

public interface ShiftService {

	public Shift getOne(Long id);
	
	public List<Shift> getAll();
	
	public Shift save(Shift shift);
	
	public Shift delete(Long id);

}
