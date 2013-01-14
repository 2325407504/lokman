package com.aripd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findOneByUsername(String username);
}
