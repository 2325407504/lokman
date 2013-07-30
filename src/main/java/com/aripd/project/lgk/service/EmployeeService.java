package com.aripd.project.lgk.service;

import com.aripd.project.lgk.domain.Employee;
import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

    public Employee findOne(Long id);

    public Employee findOneByTckimlikno(String tckimlikno);

    public List<Employee> findAll();

    public Employee save(Employee employee);

    public void delete(Long id);

    public void delete(Employee employee);

    public DatatablesResultSet<Employee> getRecords(DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void exportData(HttpServletResponse response);
}
