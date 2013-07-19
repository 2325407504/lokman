package com.aripd.project.lgk.service;

import com.aripd.account.domain.Account;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Employeeworkinghour;
import com.aripd.project.lgk.model.EmployeeworkinghourReportModel;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeworkinghourService {

    public Employeeworkinghour findOne(Long id);

    public List<Employeeworkinghour> findByAccount(Account account);

    public Employeeworkinghour save(Employeeworkinghour employeeworkinghour);

    public void delete(Long id);

    public void delete(Employeeworkinghour employeeworkinghour);

    public DatatablesResultSet<Employeeworkinghour> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Employeeworkinghour> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void exportByAccount(HttpServletResponse response, Account account);

    public List<EmployeeworkinghourReportModel> retrieveDatasource(Account account);

    public int getAnnualLeaveQualified(Account account, DateTime dt1);

    public int getAnnualLeaveUsed(Account account, DateTime dt1, DateTime dt2);
}
