package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Waste;

public interface WasteRepository extends JpaRepository<Waste, Long> {

    Waste findOneByCode(String code);
}
