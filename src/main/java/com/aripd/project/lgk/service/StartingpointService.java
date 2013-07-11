package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Startingpoint;

public interface StartingpointService {

    public Startingpoint findOne(Long id);

    public Startingpoint findOneByCode(String startingpoint_code);

    public List<Startingpoint> findAll();

    public Startingpoint save(Startingpoint startingpoint);

    public void delete(Long id);

    public void delete(Startingpoint startingpoint);

    public DatatablesResultSet<Startingpoint> getRecords(DatatablesCriteria criteria);
}
