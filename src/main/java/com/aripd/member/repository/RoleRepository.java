package com.aripd.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.member.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findOneByCode(String code);
	
}
