package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Role;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;

public interface IRoleService {

	List<Role> findAll();

	Role findOne(Long id);

	Role save(Role role);

	void delete(Long id);

	ResultSet<Role> getRecords(PagingCriteria criteria);
	
}
