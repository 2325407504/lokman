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
import com.aripd.account.repository.RoleRepository;
import com.aripd.account.service.RoleService;

/**
 * This implementation of the RoleService interface communicates with the
 * database by using a Spring Data JPA repository.
 * 
 * @author aripd.com
 */
@Service("roleService")
@Transactional(readOnly = true)
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

	public List<Role> findAll() {
		logger.debug("Finding all roles");
		return repository.findAll();
	}

	public Role findById(Long id) {
		logger.debug("Finding role by id: " + id);
		return repository.findOne(id);
	}

	@Transactional(readOnly = false)
	public Role save(Role role) {
		return repository.save(role);
	}

	@Transactional(readOnly = false)
	public void delete(Role role) {
		repository.delete(role);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(repository.findOne(id));
	}

}
