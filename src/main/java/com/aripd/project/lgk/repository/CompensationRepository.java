package com.aripd.project.lgk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Compensation;
import com.aripd.project.lgk.domain.Electricmeter;
import com.aripd.project.lgk.domain.Production;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {

    public Compensation findOneByProductionAndElectricmeter(Production production, Electricmeter electricmeter);
}
