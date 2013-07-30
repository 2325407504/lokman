package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.member.domain.Member;
import com.aripd.project.lgk.domain.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Expense findOneByMemberAndId(Member member, Long id);
}
