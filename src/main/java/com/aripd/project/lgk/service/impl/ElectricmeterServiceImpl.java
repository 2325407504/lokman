package com.aripd.project.lgk.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;
import com.aripd.project.lgk.domain.Electricmeter;
import com.aripd.project.lgk.domain.Electricmeter_;
import com.aripd.project.lgk.repository.ElectricmeterRepository;
import com.aripd.project.lgk.service.ElectricmeterService;

@Service("electricmeterService")
@Transactional(readOnly = true)
public class ElectricmeterServiceImpl implements ElectricmeterService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ElectricmeterRepository repository;

    public Electricmeter findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Electricmeter> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Electricmeter save(Electricmeter electricmeter) {
        return repository.save(electricmeter);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Electricmeter electricmeter) {
        repository.delete(electricmeter);
    }

    public ResultSet<Electricmeter> getRecords(PagingCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<SortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Electricmeter> cq = cb.createQuery(Electricmeter.class);
        Root<Electricmeter> root = cq.from(Electricmeter.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Electricmeter_.name), "%" + search + "%");
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        for (SortField sortField : sortFields) {
            String field = sortField.getField();
            String direction = sortField.getDirection().getDirection();
            if (direction.equalsIgnoreCase("asc")) {
                cq.orderBy(cb.asc(root.get(field)));
            } else if (direction.equalsIgnoreCase("desc")) {
                cq.orderBy(cb.desc(root.get(field)));
            }
        }

        Long totalRecords = (long) em.createQuery(cq).getResultList().size();

        // Pagination
        TypedQuery<Electricmeter> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Electricmeter> resultList = typedQuery.getResultList();

        return new ResultSet<Electricmeter>(resultList, totalRecords, displaySize);
    }
}
