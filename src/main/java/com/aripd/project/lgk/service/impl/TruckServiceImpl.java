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
import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.domain.Trip_;
import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.domain.Truck_;
import com.aripd.project.lgk.repository.TruckRepository;
import com.aripd.project.lgk.service.TruckService;

@Service("truckService")
@Transactional(readOnly = true)
public class TruckServiceImpl implements TruckService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private TruckRepository repository;

    public Truck findOne(Long id) {
        return repository.findOne(id);
    }

    public Truck findOneByPlate(String plate) {
        return repository.findOneByPlate(plate);
    }

    public List<Truck> findAll() {
        return repository.findAll();
    }

    public List<Truck> findByRegion(Region region) {
        return repository.findByRegion(region);
    }

    @Transactional
    public Truck save(Truck truck) {
        return repository.save(truck);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Truck truck) {
        repository.delete(truck);
    }

    public Integer getKilometer(Long id) {
        Truck truck = this.findOne(id);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> root = cq.from(Trip.class);
        
        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate = cb.equal(root.get(Trip_.truck), truck);
        predicateList.add(predicate);
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);
        
        cq.orderBy(cb.desc(root.get(Trip_.endingTime)));

        Trip trip = em.createQuery(cq).setMaxResults(1).getSingleResult();
        return trip.getEndingKm();
    }

    public ResultSet<Truck> getRecords(PagingCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<SortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Truck> cq = cb.createQuery(Truck.class);
        Root<Truck> root = cq.from(Truck.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Truck_.plate), "%" + search + "%");
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
        TypedQuery<Truck> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Truck> resultList = typedQuery.getResultList();

        return new ResultSet<Truck>(resultList, totalRecords, displaySize);
    }
}
