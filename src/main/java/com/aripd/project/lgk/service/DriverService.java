package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.domain.Region;

public interface DriverService {

    Driver findOne(Long id);

    Driver findOneByCode(String code);

    List<Driver> findAll();

    List<Driver> findByRegion(Region region);

    Driver save(Driver driver);

    void delete(Long id);

    void delete(Driver driver);

    ResultSet<Driver> getRecords(PagingCriteria criteria);
}
