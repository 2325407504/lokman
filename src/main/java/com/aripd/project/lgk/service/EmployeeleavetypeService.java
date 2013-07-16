package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Employeeleavetype;

public interface EmployeeleavetypeService {

    public Employeeleavetype findOne(Long id);

    public Employeeleavetype findOneByCode(String code);

    public List<Employeeleavetype> findAll();

    public Employeeleavetype save(Employeeleavetype employeeleavetype);

    public void delete(Long id);

    public void delete(Employeeleavetype employeeleavetype);

    public DatatablesResultSet<Employeeleavetype> getRecords(DatatablesCriteria criteria);
}
