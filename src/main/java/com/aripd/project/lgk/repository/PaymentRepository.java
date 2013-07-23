package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findOneByAccountAndId(Account account, Long id);
}
