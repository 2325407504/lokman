package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Machinetime;
import com.aripd.project.lgk.domain.Machine;
import com.aripd.project.lgk.domain.Production;

public interface MachinetimeRepository extends JpaRepository<Machinetime, Long> {

    public Machinetime findOneByProductionAndMachine(Production production, Machine machine);
}
