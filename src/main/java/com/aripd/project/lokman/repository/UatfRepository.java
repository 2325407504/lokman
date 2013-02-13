package com.aripd.project.lokman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lokman.domain.Uatf;

public interface UatfRepository extends JpaRepository<Uatf, Long> {

	List<Uatf> findByForwardingId(Long id);

}
