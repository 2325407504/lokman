package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Expense;

public interface ExpenseService {

    Expense findOne(Long id);

    Expense findOneByAccountAndId(Account account, Long id);

    Expense save(Expense expense);

    void delete(Long id);

    void delete(Expense expense);

    ResultSet<Expense> getRecords(PagingCriteria criteria);

    ResultSet<Expense> getRecords(Principal principal, PagingCriteria criteria);

    void exportXLS(HttpServletResponse response);

    void importXLSX(String fileName);
}
