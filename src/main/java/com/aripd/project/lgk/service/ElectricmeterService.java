package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Electricmeter;

public interface ElectricmeterService {

    Electricmeter findOne(Long id);

    List<Electricmeter> findAll();

    Electricmeter save(Electricmeter electricmeter);

    void delete(Long id);

    void delete(Electricmeter electricmeter);

    DatatablesResultSet<Electricmeter> getRecords(DatatablesCriteria criteria);
}
