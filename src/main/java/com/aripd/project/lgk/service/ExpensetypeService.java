package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Expensetype;

public interface ExpensetypeService {

    public Expensetype findOne(Long id);

    public Expensetype findOneByCode(String expensetype_code);

    public List<Expensetype> findAll();

    public Expensetype save(Expensetype expensetype);

    public void delete(Long id);

    public void delete(Expensetype expensetype);

    public DatatablesResultSet<Expensetype> getRecords(DatatablesCriteria criteria);
}
