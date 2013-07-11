package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Leavetype;

public interface LeavetypeService {

    public Leavetype findOne(Long id);

    public Leavetype findOneByCode(String leavetype_code);

    public List<Leavetype> findAll();

    public Leavetype save(Leavetype leavetype);

    public void delete(Long id);

    public void delete(Leavetype leavetype);

    public DatatablesResultSet<Leavetype> getRecords(DatatablesCriteria criteria);
}
