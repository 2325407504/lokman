package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Role;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;

public interface RoleService {

	List<Role> findAll();

	Role findOne(Long id);

	Role save(Role role);

	void delete(Long id);

	DatatablesResultSet<Role> getRecords(DatatablesCriteria criteria);
	
}
