package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Role;

public interface RoleService {

	public Role getOne(Long id);

	public List<Role> getAll();

	public Role save(Role role);

	public Role create(Role role);

	public Role update(Role role);

	public Role delete(Long id);

}
