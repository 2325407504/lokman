package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Disposalcost;

public interface DisposalcostService {

    public Disposalcost findOne(Long id);

    public List<Disposalcost> findAll();

    public Disposalcost save(Disposalcost disposalcost);

    public void delete(Long id);

    public void delete(Disposalcost disposalcost);
}
