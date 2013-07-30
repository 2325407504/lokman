package com.aripd.member.repository;

import com.aripd.member.domain.Memberlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberlogRepository extends JpaRepository<Memberlog, Long> {
}
