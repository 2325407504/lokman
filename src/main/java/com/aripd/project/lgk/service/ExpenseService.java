package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Expense;
import java.util.List;
import org.joda.time.DateTime;

public interface ExpenseService {

    public Expense findOne(Long id);

    public Expense findOneByAccountAndId(Account account, Long id);

    public List<Expense> findByInterval(DateTime startingTime, DateTime endingTime);

    public Expense save(Expense expense);

    public void delete(Long id);

    public void delete(Expense expense);

    public DatatablesResultSet<Expense> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Expense> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importXLSX(String fileName);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
