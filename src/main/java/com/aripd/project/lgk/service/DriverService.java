package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.domain.Region;

public interface DriverService {

    public Driver findOne(Long id);

    public Driver findOneByCode(String code);

    public List<Driver> findAll();

    public List<Driver> findByRegion(Region region);

    public Driver save(Driver driver);

    public void delete(Long id);

    public void delete(Driver driver);

    public DatatablesResultSet<Driver> getRecords(DatatablesCriteria criteria);

    public String[] getNames(String q);
}
