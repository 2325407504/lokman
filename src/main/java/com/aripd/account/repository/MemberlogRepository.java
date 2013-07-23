package com.aripd.account.repository;

import com.aripd.account.domain.Memberlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberlogRepository extends JpaRepository<Memberlog, Long> {
}
