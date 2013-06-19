package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Machine;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}
