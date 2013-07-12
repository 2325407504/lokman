package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Shippingcost;

public interface ShippingcostService {

    public Shippingcost findOne(Long id);

    public List<Shippingcost> findAll();

    public Shippingcost save(Shippingcost shippingcost);

    public void delete(Long id);

    public void delete(Shippingcost shippingcost);
}
