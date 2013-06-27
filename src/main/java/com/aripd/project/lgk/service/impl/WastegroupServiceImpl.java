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
import com.aripd.project.lgk.domain.Wastegroup;
import com.aripd.project.lgk.domain.Wastegroup_;
import com.aripd.project.lgk.repository.WastegroupRepository;
import com.aripd.project.lgk.service.WastegroupService;

@Service("wastegroupService")
@Transactional(readOnly = true)
public class WastegroupServiceImpl implements WastegroupService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private WastegroupRepository repository;

    public Wastegroup findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Wastegroup> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Wastegroup save(Wastegroup wastegroup) {
        return repository.save(wastegroup);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Wastegroup wastegroup) {
        repository.delete(wastegroup);
    }

    public ResultSet<Wastegroup> getRecords(PagingCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<SortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Wastegroup> cq = cb.createQuery(Wastegroup.class);
        Root<Wastegroup> root = cq.from(Wastegroup.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Wastegroup_.name), "%" + search + "%");
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
        TypedQuery<Wastegroup> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Wastegroup> resultList = typedQuery.getResultList();

        return new ResultSet<Wastegroup>(resultList, totalRecords, displaySize);
    }
}
