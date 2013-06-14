package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Region;

public interface RegionService {

    Region findOne(Long id);

    List<Region> findAll();

    Region save(Region region);

    void delete(Long id);

    void delete(Region region);

    ResultSet<Region> getRecords(PagingCriteria criteria);
}
