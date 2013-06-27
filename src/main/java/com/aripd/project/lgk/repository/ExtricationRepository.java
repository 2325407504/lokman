package com.aripd.project.lgk.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Extrication;
import com.aripd.project.lgk.domain.Waste;
import com.aripd.project.lgk.domain.Weighbridge;

public interface ExtricationRepository extends JpaRepository<Extrication, Long> {

    public Extrication findOneByWeighbridgeAndWaste(Weighbridge weighbridge, Waste waste);
}
