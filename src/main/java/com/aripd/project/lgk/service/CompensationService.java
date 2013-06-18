package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Compensation;
import com.aripd.project.lgk.domain.Electricmeter;
import com.aripd.project.lgk.domain.Production;

public interface CompensationService {

    public Compensation findOne(Long id);

    public Compensation findOneByProductionAndElectricmeter(Production production, Electricmeter electricmeter);

    public List<Compensation> findAll();

    public Compensation save(Compensation compensation);

    public void delete(Long id);

    public void delete(Compensation compensation);
}
