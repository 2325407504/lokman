package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Machinetime;
import com.aripd.project.lgk.domain.Machine;
import com.aripd.project.lgk.domain.Production;

public interface MachinetimeService {

    public Machinetime findOne(Long id);

    public Machinetime findOneByProductionAndMachine(Production production, Machine machine);

    public List<Machinetime> findAll();

    public List<Machinetime> findByProduction(Production production);

    public Machinetime save(Machinetime machinetime);

    public void delete(Long id);

    public void delete(Machinetime machinetime);
}
