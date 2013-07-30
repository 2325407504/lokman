package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.member.domain.Member;
import com.aripd.project.lgk.domain.Waybill;

public interface WaybillRepository extends JpaRepository<Waybill, Long> {

    public Waybill findOneByMemberAndId(Member member, Long id);

    public Waybill findOneByDocumentNo(String documentNo);
}
