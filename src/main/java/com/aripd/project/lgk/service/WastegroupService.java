package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Wastegroup;

public interface WastegroupService {

    Wastegroup findOne(Long id);

    List<Wastegroup> findAll();

    Wastegroup save(Wastegroup wastegroup);

    void delete(Long id);

    void delete(Wastegroup wastegroup);

    ResultSet<Wastegroup> getRecords(PagingCriteria criteria);
}
