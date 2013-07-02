package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Productgroup;

public interface ProductgroupService {

    Productgroup findOne(Long id);

    List<Productgroup> findAll();

    Productgroup save(Productgroup productgroup);

    void delete(Long id);

    void delete(Productgroup productgroup);

    DatatablesResultSet<Productgroup> getRecords(DatatablesCriteria criteria);
}
