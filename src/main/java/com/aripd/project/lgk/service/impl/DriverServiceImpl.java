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
import com.aripd.project.lgk.domain.Driver;
import com.aripd.project.lgk.domain.Driver_;
import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.domain.Truck_;
import com.aripd.project.lgk.repository.DriverRepository;
import com.aripd.project.lgk.service.DriverService;

@Service("driverService")
@Transactional(readOnly = true)
public class DriverServiceImpl implements DriverService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private DriverRepository repository;

    public Driver findOne(Long id) {
        return repository.findOne(id);
    }

    public Driver findOneByCode(String code) {
        return repository.findOneByCode(code);
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public List<Driver> findByRegion(Region region) {
        return repository.findByRegion(region);
    }

    @Transactional
    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Driver driver) {
        repository.delete(driver);
    }

    public DatatablesResultSet<Driver> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);
        Root<Driver> root = cq.from(Driver.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Driver_.code), "%" + search + "%");
            Predicate predicate2 = cb.like(root.get(Driver_.name), "%" + search + "%");
            Predicate predicate3 = cb.like(root.get(Driver_.phonenumber), "%" + search + "%");
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
        TypedQuery<Driver> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Driver> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Driver>(resultList, totalRecords, displaySize);
    }

    public String[] getNames(String q) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);
        Root<Driver> root = cq.from(Driver.class);

        Predicate predicate = cb.like(root.get(Driver_.name), "%" + q + "%");
        cq.where(predicate);
        cq.orderBy(cb.desc(root.get(Driver_.name)));

        List<Driver> drivers = em.createQuery(cq).getResultList();
        String[] a = new String[drivers.size()];
        int index = 0;
        for (Driver driver : drivers) {
            a[index] = (String) driver.getName();
            index++;
        }
        return a;
    }
}
