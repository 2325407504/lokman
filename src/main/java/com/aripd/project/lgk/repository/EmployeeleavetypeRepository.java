package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Employeeleavetype;

public interface EmployeeleavetypeRepository extends JpaRepository<Employeeleavetype, Long> {

    public Employeeleavetype findOneByCode(String code);
}
