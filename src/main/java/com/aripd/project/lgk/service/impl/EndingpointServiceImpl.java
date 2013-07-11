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
import com.aripd.project.lgk.domain.Endingpoint;
import com.aripd.project.lgk.domain.Endingpoint_;
import com.aripd.project.lgk.repository.EndingpointRepository;
import com.aripd.project.lgk.service.EndingpointService;

@Service("endingpointService")
@Transactional(readOnly = true)
public class EndingpointServiceImpl implements EndingpointService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EndingpointRepository repository;

    public Endingpoint findOne(Long id) {
        return repository.findOne(id);
    }

    public Endingpoint findOneByCode(String code) {
        return repository.findOneByCode(code);
    }
    public List<Endingpoint> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Endingpoint save(Endingpoint endingpoint) {
        return repository.save(endingpoint);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Endingpoint endingpoint) {
        repository.delete(endingpoint);
    }

    public DatatablesResultSet<Endingpoint> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Endingpoint> cq = cb.createQuery(Endingpoint.class);
        Root<Endingpoint> root = cq.from(Endingpoint.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Endingpoint_.name), "%" + search + "%");
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
        TypedQuery<Endingpoint> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Endingpoint> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Endingpoint>(resultList, totalRecords, displaySize);
    }
}
