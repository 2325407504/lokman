package com.aripd.project.lgk.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Compensation;
import com.aripd.project.lgk.domain.Electricmeter;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.repository.CompensationRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.CompensationService;
import com.aripd.project.lgk.service.ProductService;
import com.aripd.project.lgk.service.ShiftService;

@Service("compensationService")
@Transactional
public class CompensationServiceImpl implements CompensationService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CompensationRepository repository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private ProductService productService;

    @Transactional(readOnly = true)
    public Compensation findOne(Long id) {
        return repository.findOne(id);
    }

    @Transactional(readOnly = true)
    public Compensation findOneByProductionAndElectricmeter(Production production, Electricmeter electricmeter) {
        return repository.findOneByProductionAndElectricmeter(production, electricmeter);
    }

    @Transactional(readOnly = true)
    public List<Compensation> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = false)
    public Compensation save(Compensation compensation) {
        return repository.save(compensation);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional(readOnly = false)
    public void delete(Compensation compensation) {
        repository.delete(compensation);
    }
}
