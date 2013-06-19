package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Machine;

public interface MachineService {

    Machine findOne(Long id);

    List<Machine> findAll();

    Machine save(Machine machine);

    void delete(Long id);

    void delete(Machine machine);

    ResultSet<Machine> getRecords(PagingCriteria criteria);
}
