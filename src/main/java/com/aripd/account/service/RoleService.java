package com.aripd.account.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Role;
import com.aripd.account.repository.RoleRepository;

@Service("roleService")
@Transactional
public class RoleService {

	protected static Logger logger4J = Logger.getLogger(RoleService.class);

	@Autowired
	private RoleRepository repository;

	public Role getOne(Long id) {
		logger4J.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<Role> getAll() {
		logger4J.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Boolean save(Role role) {
		//Role existing = repository.findOneByCode(role.getCode());
		//if (existing != null)
			//return false;

		Role saved = repository.save(role);
		if (saved == null) {
			logger4J.debug("Save error");
			return false;
		}
		
		logger4J.debug("Saved");
		return true;
	}

	public Boolean update(Role role) {
		Role existing = repository.findOne(role.getId());
		if (existing == null)
			return false;

		existing.setCode(role.getCode());
		existing.setName(role.getName());

		Role saved = repository.save(existing);
		if (saved == null)
			return false;

		logger4J.debug("Editing existing person");
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

		logger4J.debug("Deleting existing person");
		return true;
	}

}
