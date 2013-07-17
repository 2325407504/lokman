package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Proc;

public interface ProcService {

    public Proc findOne(Long id);

    public List<Proc> findAll();

    public List<Proc> findByActive(boolean active);

    public Proc save(Proc proc);

    public void delete(Long id);

    public void delete(Proc proc);

    public DatatablesResultSet<Proc> getRecords(DatatablesCriteria criteria);
}
