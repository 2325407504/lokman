package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Bigbag;

public interface BigbagService {

    Bigbag findOne(Long id);

    List<Bigbag> findAll();

    Bigbag save(Bigbag bigbag);

    void delete(Long id);

    void delete(Bigbag bigbag);

    List<Bigbag> findByProductionId(Long id);

    ResultSet<Bigbag> getRecords(Long production_id, PagingCriteria criteria);

    void importXLSX(String fileName);
}
