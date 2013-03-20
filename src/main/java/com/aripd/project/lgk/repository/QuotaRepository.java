package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Quota;

public interface QuotaRepository extends JpaRepository<Quota, Long> {

	Quota findOneByCode(String code);

}
