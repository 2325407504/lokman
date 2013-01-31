package com.aripd.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Role;

/**
 * Specifies methods used to obtain and modify role related information which
 * is stored in the database.
 * 
 * @author aripd.com
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findOneByCode(String code);
	
}
