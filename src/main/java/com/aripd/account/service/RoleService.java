package com.aripd.account.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aripd.account.domain.Role;

public interface RoleService {

	Role getOne(Long id);

	Page<Role> getAll(Integer pageNumber);

	List<Role> getAll();

	Role save(Role role);

	void delete(Long id);
	
	void delete(Role role);

	List<Role> findAll();

	Role findById(Long id);

}
