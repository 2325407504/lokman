package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Endingpoint;

public interface EndingpointRepository extends JpaRepository<Endingpoint, Long> {

    public Endingpoint findOneByCode(String code);
}
