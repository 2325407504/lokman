package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Employee;
import com.aripd.project.lgk.domain.Employeeworkinghour;
import com.aripd.project.lgk.model.EmployeeworkinghourFilterByIntervalForm;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeworkinghourService {

    public Employeeworkinghour findOne(Long id);

    public List<Employeeworkinghour> findByEmployee(Employee employee);

    public Employeeworkinghour save(Employeeworkinghour employeeworkinghour);

    public void delete(Long id);

    public void delete(Employeeworkinghour employeeworkinghour);

    public DatatablesResultSet<Employeeworkinghour> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Employeeworkinghour> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file, Principal principal);

    public void export(HttpServletResponse response, EmployeeworkinghourFilterByIntervalForm formData);

    public List<Employeeworkinghour> retrieveDatasource(EmployeeworkinghourFilterByIntervalForm formData);
}
