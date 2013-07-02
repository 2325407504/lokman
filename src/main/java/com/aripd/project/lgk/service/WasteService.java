package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Waste;

public interface WasteService {

    Waste findOne(Long id);

    Waste findOneByCode(String code);

    List<Waste> findAll();

    Waste save(Waste waste);

    void delete(Long id);

    void delete(Waste waste);

    DatatablesResultSet<Waste> getRecords(DatatablesCriteria criteria);
}
