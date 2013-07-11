package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Startingpoint;

public interface StartingpointRepository extends JpaRepository<Startingpoint, Long> {

    public Startingpoint findOneByCode(String code);
}
