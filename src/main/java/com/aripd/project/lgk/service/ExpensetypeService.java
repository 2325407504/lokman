package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Expensetype;

public interface ExpensetypeService {

    Expensetype findOne(Long id);

    List<Expensetype> findAll();

    Expensetype save(Expensetype expensetype);

    void delete(Long id);

    void delete(Expensetype expensetype);

    DatatablesResultSet<Expensetype> getRecords(DatatablesCriteria criteria);
}
