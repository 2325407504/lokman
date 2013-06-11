package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Electricmeter;

public interface ElectricmeterService {

    Electricmeter findOne(Long id);

    List<Electricmeter> findAll();

    Electricmeter save(Electricmeter electricmeter);

    void delete(Long id);

    void delete(Electricmeter electricmeter);

    ResultSet<Electricmeter> getRecords(PagingCriteria criteria);
}
