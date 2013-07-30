package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.member.domain.Member;
import com.aripd.project.lgk.domain.Production;
import org.joda.time.DateTime;

public interface ProductionRepository extends JpaRepository<Production, Long> {

    public Production findOneByMemberAndId(Member member, Long id);

    public Production findOneByShiftdate(DateTime dateTime);
}
