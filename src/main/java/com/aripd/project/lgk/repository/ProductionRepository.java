package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Production;
import org.joda.time.DateTime;

public interface ProductionRepository extends JpaRepository<Production, Long> {

    Production findOneByAccountAndId(Account account, Long id);

    Production findOneByShiftdate(DateTime dateTime);
}
