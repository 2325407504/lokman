package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Forwarding;

public interface ForwardingRepository extends JpaRepository<Forwarding, Long> {

	Forwarding findOneByAccountAndId(Account account, Long id);
	
}
