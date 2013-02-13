package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;

public interface AccountService {

	Account getOne(Long id);

	Account getOneByUsername(String username);

	Account getActiveUser();

	boolean hasUsername(String username);

	List<Account> getAll();

	Account save(Account account);

	void delete(Long id);

	List<Account> findAll();

	ResultSet<Account> getRecords(PagingCriteria criteria);

}
