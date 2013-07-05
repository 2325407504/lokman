package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Expensetype;

public interface ExpensetypeRepository extends JpaRepository<Expensetype, Long> {

    public Expensetype findOneByCode(String code);
}
