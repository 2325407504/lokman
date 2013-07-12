package com.aripd.project.lgk.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Disposalcost;
import com.aripd.project.lgk.repository.DisposalcostRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.DisposalcostService;
import com.aripd.project.lgk.service.ProductService;

@Service("disposalcostService")
@Transactional(readOnly = true)
public class DisposalcostServiceImpl implements DisposalcostService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private DisposalcostRepository repository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ProductService productService;

    public Disposalcost findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Disposalcost> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Disposalcost save(Disposalcost disposalcost) {
        return repository.save(disposalcost);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Disposalcost disposalcost) {
        repository.delete(disposalcost);
    }
}
