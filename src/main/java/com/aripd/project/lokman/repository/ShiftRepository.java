package com.aripd.project.lokman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Account;
import com.aripd.project.lokman.domain.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

	List<Account> findByAccount(Account account);
	
}
