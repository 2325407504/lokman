package com.aripd.project.lgk.service;

import com.aripd.member.domain.Member;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Employee;
import com.aripd.project.lgk.domain.Employeeleave;
import com.aripd.project.lgk.model.EmployeeleaveReportModel;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeleaveService {

    public Employeeleave findOne(Long id);

    public List<Employeeleave> findByEmployee(Employee employee);

    public Employeeleave save(Employeeleave employeeleave);

    public void delete(Long id);

    public void delete(Employeeleave employeeleave);

    public DatatablesResultSet<Employeeleave> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Employeeleave> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void export(HttpServletResponse response, Member member);

    public List<EmployeeleaveReportModel> retrieveDatasource(Member member);

    public int getAnnualLeaveQualified(Member member, DateTime dt1);

    public int getAnnualLeaveUsed(Member member, DateTime dt1, DateTime dt2);
}
