package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Expensetype;

public interface ExpensetypeService {

    Expensetype findOne(Long id);

    List<Expensetype> findAll();

    Expensetype save(Expensetype expensetype);

    void delete(Long id);

    void delete(Expensetype expensetype);

    ResultSet<Expensetype> getRecords(PagingCriteria criteria);
}
