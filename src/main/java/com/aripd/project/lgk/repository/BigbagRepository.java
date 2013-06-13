package com.aripd.project.lgk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Bigbag;

public interface BigbagRepository extends JpaRepository<Bigbag, Long> {

    List<Bigbag> findByProductionId(Long id);
}
