package com.aripd.project.lgk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Quota;
import com.aripd.project.lgk.repository.QuotaRepository;
import com.aripd.project.lgk.service.QuotaService;

@Service("quotaService")
@Transactional
public class QuotaServiceImpl implements QuotaService {

	@Autowired
	private QuotaRepository repository;
	
	public Quota findOne(Long id) {
		return repository.findOne(id);
	}

	public List<Quota> findAll() {
		return repository.findAll();
	}

	public Quota save(Quota quota) {
		return repository.save(quota);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}

	public void delete(Quota quota) {
		repository.delete(quota);
	}

}
