package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Endingpoint;

public interface EndingpointService {

    public Endingpoint findOne(Long id);

    public Endingpoint findOneByCode(String endingpoint_code);

    public List<Endingpoint> findAll();

    public Endingpoint save(Endingpoint endingpoint);

    public void delete(Long id);

    public void delete(Endingpoint endingpoint);

    public DatatablesResultSet<Endingpoint> getRecords(DatatablesCriteria criteria);
}
