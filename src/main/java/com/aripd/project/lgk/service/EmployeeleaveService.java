package com.aripd.project.lgk.service;

import com.aripd.account.domain.Account;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Employeeleave;
import com.aripd.project.lgk.model.EmployeeleaveReportModel;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeleaveService {

    public Employeeleave findOne(Long id);

    public List<Employeeleave> findByAccount(Account account);

    public Employeeleave save(Employeeleave leave);

    public void delete(Long id);

    public void delete(Employeeleave leave);

    public DatatablesResultSet<Employeeleave> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Employeeleave> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void exportByAccount(HttpServletResponse response, Account account);

    public List<EmployeeleaveReportModel> retrieveDatasource(Account account);

    public int getAnnualLeaveQualified(Account account, DateTime dt1);

    public int getAnnualLeaveUsed(Account account, DateTime dt1, DateTime dt2);
}
