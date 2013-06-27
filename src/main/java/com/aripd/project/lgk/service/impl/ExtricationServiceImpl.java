package com.aripd.project.lgk.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Extrication;
import com.aripd.project.lgk.domain.Waste;
import com.aripd.project.lgk.domain.Weighbridge;
import com.aripd.project.lgk.repository.ExtricationRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.ExtricationService;
import com.aripd.project.lgk.service.ProductService;
import com.aripd.project.lgk.service.ShiftService;

@Service("extricationService")
@Transactional
public class ExtricationServiceImpl implements ExtricationService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ExtricationRepository repository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private ProductService productService;

    @Transactional(readOnly = true)
    public Extrication findOne(Long id) {
        return repository.findOne(id);
    }

    @Transactional(readOnly = true)
    public Extrication findOneByWeighbridgeAndWaste(Weighbridge weighbridge, Waste waste) {
        return repository.findOneByWeighbridgeAndWaste(weighbridge, waste);
    }

    @Transactional(readOnly = true)
    public List<Extrication> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = false)
    public Extrication save(Extrication extrication) {
        return repository.save(extrication);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional(readOnly = false)
    public void delete(Extrication extrication) {
        repository.delete(extrication);
    }
}
