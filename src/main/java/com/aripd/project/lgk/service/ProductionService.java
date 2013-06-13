package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.domain.Shift;
import org.joda.time.DateTime;

public interface ProductionService {

    public Production findOne(Long id);

    public Production findOneByAccountAndId(Account account, Long id);

    public Production findOneByShiftdateAndShift(DateTime dateTime, Shift shift);

    public Production save(Production formData);

    public void delete(Long id);

    public void delete(Production production);

    public ResultSet<Production> getRecords(PagingCriteria criteria);

    public ResultSet<Production> getRecords(Principal principal, PagingCriteria criteria);

    public void exportXLS(HttpServletResponse response);

    public void importXLSX(String fileName);

    public void importCSV(String content);
}
