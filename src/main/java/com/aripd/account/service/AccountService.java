package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;

public interface AccountService {

	List<Account> findAll();

	Account findOne(Long id);

	Account findOneByUsername(String username);

	Account findCurrentUser();

	boolean hasUsername(String username);

	Account save(Account account);

	void delete(Long id);

	ResultSet<Account> getRecords(PagingCriteria criteria);

}
