package com.aripd.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Role;
import com.aripd.account.repository.RoleRepository;

@Service
@Transactional(readOnly = true)
public class RoleService implements IRoleService {

	protected static Logger logger = Logger.getLogger(RoleService.class);
	
	@Resource
	private RoleRepository repository;

	public List<Role> findAll() {
		logger.debug("Finding all records");
		return repository.findAll();
	}

	public Role findOne(Long id) {
		logger.debug("Finding a record by id: " + id);
		return repository.findOne(id);
	}

	@Transactional
	public Role save(Role role) {
		return repository.save(role);
	}

	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}

}
