package com.aripd.account.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Role;
import com.aripd.account.repository.RoleRepository;
import com.aripd.account.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	protected static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleRepository repository;

	public Role getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<Role> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Role save(Role role) {
		return repository.save(role);
	}

	public Role update(Role role) {
		Role existing = repository.findOne(role.getId());

		existing.setCode(role.getCode());
		existing.setName(role.getName());

		return repository.save(existing);
	}

	public Role delete(Long id) {
		Role role = repository.findOne(id);
		repository.delete(id);
		return role;
	}

	public Role create(Role role) {
		return repository.save(role);
	}

}
