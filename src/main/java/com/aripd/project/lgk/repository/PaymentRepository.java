package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.member.domain.Member;
import com.aripd.project.lgk.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    public Payment findOneByMemberAndId(Member member, Long id);
}
