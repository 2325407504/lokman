package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Role;

public interface IRoleService {

	List<Role> findAll();

	Role findOne(Long id);

	Role save(Role role);

	void delete(Long id);
	
}
