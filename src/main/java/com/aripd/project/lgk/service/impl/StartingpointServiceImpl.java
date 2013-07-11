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

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesSortField;
import com.aripd.project.lgk.domain.Startingpoint;
import com.aripd.project.lgk.domain.Startingpoint_;
import com.aripd.project.lgk.repository.StartingpointRepository;
import com.aripd.project.lgk.service.StartingpointService;

@Service("startingpointService")
@Transactional(readOnly = true)
public class StartingpointServiceImpl implements StartingpointService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private StartingpointRepository repository;

    public Startingpoint findOne(Long id) {
        return repository.findOne(id);
    }

    public Startingpoint findOneByCode(String code) {
        return repository.findOneByCode(code);
    }
    public List<Startingpoint> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Startingpoint save(Startingpoint startingpoint) {
        return repository.save(startingpoint);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Startingpoint startingpoint) {
        repository.delete(startingpoint);
    }

    public DatatablesResultSet<Startingpoint> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Startingpoint> cq = cb.createQuery(Startingpoint.class);
        Root<Startingpoint> root = cq.from(Startingpoint.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Startingpoint_.name), "%" + search + "%");
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        for (DatatablesSortField sortField : sortFields) {
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
        TypedQuery<Startingpoint> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Startingpoint> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Startingpoint>(resultList, totalRecords, displaySize);
    }
}
