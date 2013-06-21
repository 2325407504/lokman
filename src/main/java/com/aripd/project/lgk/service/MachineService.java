package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Machine;

public interface MachineService {

    public Machine findOne(Long id);

    public List<Machine> findAll();

    public Machine save(Machine machine);

    public void delete(Long id);

    public void delete(Machine machine);

    public ResultSet<Machine> getRecords(PagingCriteria criteria);
}
