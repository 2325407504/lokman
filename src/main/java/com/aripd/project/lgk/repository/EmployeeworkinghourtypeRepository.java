package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Employeeworkinghourtype;

public interface EmployeeworkinghourtypeRepository extends JpaRepository<Employeeworkinghourtype, Long> {

    public Employeeworkinghourtype findOneByCode(String code);
}
