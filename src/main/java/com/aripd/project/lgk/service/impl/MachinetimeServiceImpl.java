package com.aripd.project.lgk.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Machinetime;
import com.aripd.project.lgk.domain.Machine;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.repository.MachinetimeRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.MachinetimeService;
import com.aripd.project.lgk.service.ProductService;

@Service("machinetimeService")
@Transactional(readOnly = true)
public class MachinetimeServiceImpl implements MachinetimeService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MachinetimeRepository repository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ProductService productService;

    public Machinetime findOne(Long id) {
        return repository.findOne(id);
    }

    public Machinetime findOneByProductionAndMachine(Production production, Machine machine) {
        return repository.findOneByProductionAndMachine(production, machine);
    }

    public List<Machinetime> findAll() {
        return repository.findAll();
    }

    public List<Machinetime> findByProduction(Production production) {
        return repository.findByProduction(production);
    }

    @Transactional
    public Machinetime save(Machinetime machinetime) {
        return repository.save(machinetime);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Machinetime machinetime) {
        repository.delete(machinetime);
    }
}
