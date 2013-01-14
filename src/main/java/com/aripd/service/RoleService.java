package com.aripd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.domain.Role;
import com.aripd.repository.RoleRepository;

@Service("roleService")
@Transactional
public class RoleService {

	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	private RoleRepository repository;

	public Role get(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<Role> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Boolean save(Role role) {
		Role existing = repository.findOne(role.getId());
		if (existing != null)
			return false;

		Role saved = repository.save(role);
		if (saved == null)
			return false;

		logger.debug("Adding new person");
		return true;
	}

	public Boolean update(Role role) {
		Role existing = repository.findOne(role.getId());
		if (existing == null)
			return false;

		existing.setRole(role.getRole());

		Role saved = repository.save(existing);
		if (saved == null)
			return false;

		logger.debug("Editing existing person");
		return true;
	}

	public void delete(Long id) {
		repository.delete(repository.findOne(id));
	}

	public Boolean delete(Role role) {
		Role existing = repository.findOne(role.getId());
		if (existing == null)
			return false;

		repository.delete(existing);
		Role deleted = repository.findOne(role.getId());
		if (deleted != null)
			return false;

		logger.debug("Deleting existing person");
		return true;
	}

}
