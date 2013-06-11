package com.aripd.project.lgk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Uatf;

public interface UatfRepository extends JpaRepository<Uatf, Long> {

    List<Uatf> findByForwardingId(Long id);
}
