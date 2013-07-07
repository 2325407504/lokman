package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Quota;

public interface QuotaService {

    public Quota findOne(Long id);

    public Quota findOneByCode(String code);

    public List<Quota> findAll();

    public Quota save(Quota quota);

    public void delete(Long id);

    public void delete(Quota quota);

    public DatatablesResultSet<Quota> getRecords(DatatablesCriteria criteria);
}
