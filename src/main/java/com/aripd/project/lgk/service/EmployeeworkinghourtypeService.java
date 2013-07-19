package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Employeeworkinghourtype;

public interface EmployeeworkinghourtypeService {

    public Employeeworkinghourtype findOne(Long id);

    public Employeeworkinghourtype findOneByCode(String code);

    public List<Employeeworkinghourtype> findAll();

    public Employeeworkinghourtype save(Employeeworkinghourtype employeeworkinghourtype);

    public void delete(Long id);

    public void delete(Employeeworkinghourtype employeeworkinghourtype);

    public DatatablesResultSet<Employeeworkinghourtype> getRecords(DatatablesCriteria criteria);
}
