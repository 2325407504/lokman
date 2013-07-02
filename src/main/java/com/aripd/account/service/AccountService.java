package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;

public interface AccountService {

	List<Account> findAll();

	Account findOne(Long id);

	Account findOneByUsername(String username);

	Account save(Account account);

	void delete(Long id);

	DatatablesResultSet<Account> getRecords(DatatablesCriteria criteria);

}
