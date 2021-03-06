package com.aripd.project.lgk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Outgoing;

public interface OutgoingRepository extends JpaRepository<Outgoing, Long> {

    List<Outgoing> findByWaybillId(Long waybill_id);
}
