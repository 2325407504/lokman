package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Extrication;
import com.aripd.project.lgk.domain.Waste;
import com.aripd.project.lgk.domain.Weighbridge;

public interface ExtricationService {

    public Extrication findOne(Long id);

    public Extrication findOneByWeighbridgeAndWaste(Weighbridge weighbridge, Waste waste);

    public List<Extrication> findAll();

    public Extrication save(Extrication extrication);

    public void delete(Long id);

    public void delete(Extrication extrication);
}
