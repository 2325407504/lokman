package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	Expense findOneByAccountAndId(Account account, Long id);

}
