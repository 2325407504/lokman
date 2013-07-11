package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Leavetype;

public interface LeavetypeRepository extends JpaRepository<Leavetype, Long> {

    public Leavetype findOneByCode(String code);
}
