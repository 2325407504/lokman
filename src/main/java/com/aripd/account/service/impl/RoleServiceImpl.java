package com.aripd.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Role;
import com.aripd.account.dto.RoleDto;
import com.aripd.account.exception.RoleNotFoundException;
import com.aripd.account.repository.RoleRepository;
import com.aripd.account.service.RoleService;

/**
 * This implementation of the RoleService interface communicates with the
 * database by using a Spring Data JPA repository.
 * 
 * @author aripd.com
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	protected static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	private static final int PAGE_SIZE = 5;
	
	@Resource
	private RoleRepository repository;

	/**
	 * This setter method should be used only by unit tests.
	 * 
	 * @param repository
	 */
	protected void setRoleRepository(RoleRepository repository) {
		this.repository = repository;
	}
	
	public Role getOne(Long id) {
		logger.debug("Retrieving role based on his id");
		return repository.findOne(id);
	}

	public Page<Role> getAll(Integer pageNumber) {
		PageRequest pageRequest =
	            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return repository.findAll(pageRequest);
	}

	public List<Role> getAll() {
		logger.debug("Retrieving all roles");
		return repository.findAll();
	}

	public Role save(RoleDto formData) throws RoleNotFoundException {
		if (formData.getId() == null)
			return this.create(formData);
		else
			return this.update(formData);
	}

	@Transactional
	@Override
	public Role create(RoleDto created) {
		logger.debug("Creating a new role with information: " + created);

		Role role = Role.getBuilder(created.getCode(),
				created.getName()).build();

		return repository.save(role);
	}

	@Transactional(rollbackFor = RoleNotFoundException.class)
	@Override
	public Role delete(Long id) throws RoleNotFoundException {
		logger.debug("Deleting role with id: " + id);

		Role deleted = repository.findOne(id);

		if (deleted == null) {
			logger.debug("No accoutn found with id: " + id);
			throw new RoleNotFoundException();
		}

		repository.delete(deleted);
		return deleted;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> findAll() {
		logger.debug("Finding all roles");
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Role findById(Long id) {
		logger.debug("Finding role by id: " + id);
		return repository.findOne(id);
	}

	@Transactional(rollbackFor = RoleNotFoundException.class)
	@Override
	public Role update(RoleDto updated) throws RoleNotFoundException {
		logger.debug("Updating role with information: " + updated);

		Role role = repository.findOne(updated.getId());

		if (role == null) {
			logger.debug("No role found with id: " + updated.getId());
			throw new RoleNotFoundException();
		}

		role.update(updated.getCode(), updated.getName());

		return role;
	}

}
