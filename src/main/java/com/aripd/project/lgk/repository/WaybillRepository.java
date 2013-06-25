package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Waybill;

public interface WaybillRepository extends JpaRepository<Waybill, Long> {

    Waybill findOneByAccountAndId(Account account, Long id);

    Waybill findOneByDocumentNo(String documentNo);
}
