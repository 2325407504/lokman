package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.member.domain.Member;
import com.aripd.project.lgk.domain.Forwarding;

public interface ForwardingRepository extends JpaRepository<Forwarding, Long> {

    Forwarding findOneByMemberAndId(Member member, Long id);

    Forwarding findOneByWaybillNo(String waybillNo);
}
