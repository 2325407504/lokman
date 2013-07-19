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
import com.aripd.project.lgk.domain.Employeeworkinghourtype;
import com.aripd.project.lgk.domain.Employeeworkinghourtype_;
import com.aripd.project.lgk.repository.EmployeeworkinghourtypeRepository;
import com.aripd.project.lgk.service.EmployeeworkinghourtypeService;

@Service("employeeworkinghourtypeService")
@Transactional(readOnly = true)
public class EmployeeworkinghourtypeServiceImpl implements EmployeeworkinghourtypeService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EmployeeworkinghourtypeRepository repository;

    public Employeeworkinghourtype findOne(Long id) {
        return repository.findOne(id);
    }

    public Employeeworkinghourtype findOneByCode(String code) {
        return repository.findOneByCode(code);
    }
    public List<Employeeworkinghourtype> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Employeeworkinghourtype save(Employeeworkinghourtype employeeworkinghourtype) {
        return repository.save(employeeworkinghourtype);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Employeeworkinghourtype employeeworkinghourtype) {
        repository.delete(employeeworkinghourtype);
    }

    public DatatablesResultSet<Employeeworkinghourtype> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeworkinghourtype> cq = cb.createQuery(Employeeworkinghourtype.class);
        Root<Employeeworkinghourtype> root = cq.from(Employeeworkinghourtype.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Employeeworkinghourtype_.name), "%" + search + "%");
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
        TypedQuery<Employeeworkinghourtype> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Employeeworkinghourtype> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Employeeworkinghourtype>(resultList, totalRecords, displaySize);
    }
}
