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
import com.aripd.project.lgk.domain.Proc;
import com.aripd.project.lgk.domain.Proc_;
import com.aripd.project.lgk.repository.ProcRepository;
import com.aripd.project.lgk.service.ProcService;

@Service("procService")
@Transactional(readOnly = true)
public class ProcServiceImpl implements ProcService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ProcRepository repository;

    public Proc findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Proc> findAll() {
        return repository.findAll();
    }

    public List<Proc> findByActive(boolean active) {
        return repository.findByActive(active);
    }

    @Transactional
    public Proc save(Proc proc) {
        return repository.save(proc);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Proc proc) {
        repository.delete(proc);
    }

    public DatatablesResultSet<Proc> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Proc> cq = cb.createQuery(Proc.class);
        Root<Proc> root = cq.from(Proc.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Proc_.name), "%" + search + "%");
            Predicate predicate2 = cb.like(root.get(Proc_.description), "%" + search + "%");
            Predicate predicate3 = cb.like(root.get(Proc_.content), "%" + search + "%");
            Predicate predicate = cb.or(predicate1, predicate2, predicate3);
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
        TypedQuery<Proc> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Proc> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Proc>(resultList, totalRecords, displaySize);
    }
}
