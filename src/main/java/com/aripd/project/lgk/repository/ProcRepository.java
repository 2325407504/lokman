package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Proc;
import java.util.List;

public interface ProcRepository extends JpaRepository<Proc, Long> {

    public List<Proc> findByActive(boolean active);
}
