package com.aripd.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findOneByCode(String code);
	
}
