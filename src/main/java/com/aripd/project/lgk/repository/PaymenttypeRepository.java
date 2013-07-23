package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Paymenttype;

public interface PaymenttypeRepository extends JpaRepository<Paymenttype, Long> {

    public Paymenttype findOneByCode(String code);
}
