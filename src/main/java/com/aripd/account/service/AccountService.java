package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;

public interface AccountService {

    public List<Account> findAll();

    public Account findOne(Long id);

    public Account findOneByUsername(String username);

    public Account save(Account account);

    public void delete(Long id);

    public DatatablesResultSet<Account> getRecords(DatatablesCriteria criteria);
}
