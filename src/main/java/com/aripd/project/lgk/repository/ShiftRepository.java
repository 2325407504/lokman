package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    Shift findOneByCode(String code);
}
