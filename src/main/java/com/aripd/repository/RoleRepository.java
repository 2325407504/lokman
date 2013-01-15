package com.aripd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findOneByCode(String code);
	
}
