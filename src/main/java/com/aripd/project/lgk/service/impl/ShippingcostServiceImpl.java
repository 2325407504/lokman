package com.aripd.project.lgk.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Shippingcost;
import com.aripd.project.lgk.repository.ShippingcostRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.ShippingcostService;
import com.aripd.project.lgk.service.ProductService;

@Service("shippingcostService")
@Transactional(readOnly = true)
public class ShippingcostServiceImpl implements ShippingcostService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ShippingcostRepository repository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ProductService productService;

    public Shippingcost findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Shippingcost> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Shippingcost save(Shippingcost shippingcost) {
        return repository.save(shippingcost);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Shippingcost shippingcost) {
        repository.delete(shippingcost);
    }
}
