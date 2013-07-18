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
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Forwarding_;
import com.aripd.project.lgk.domain.Region;
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

    public DatatablesResultSet<Truck> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
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
        TypedQuery<Truck> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Truck> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Truck>(resultList, totalRecords, displaySize);
    }

    public Integer getKilometer(String plate) {
        Integer kilometer = 0;
        if (plate == null) {
            return kilometer;
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Forwarding> cq = cb.createQuery(Forwarding.class);
        Root<Forwarding> root = cq.from(Forwarding.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate = cb.equal(root.get(Forwarding_.plate), plate);
        cq.where(predicate);
        cq.orderBy(cb.desc(root.get(Forwarding_.endingTime)));

        List<Forwarding> results = em.createQuery(cq).getResultList();
        if (!results.isEmpty()) {
            kilometer = results.get(0).getEndingKm();
        }
        return kilometer;
    }

    public String[] getPlates(String q) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Truck> cq = cb.createQuery(Truck.class);
        Root<Truck> root = cq.from(Truck.class);

        Predicate predicate = cb.like(root.get(Truck_.plate), "%" + q + "%");
        cq.where(predicate);
        cq.orderBy(cb.desc(root.get(Truck_.plate)));

        List<Truck> trucks = em.createQuery(cq).getResultList();
        String[] a = new String[trucks.size()];
        int index = 0;
        for (Truck truck : trucks) {
            a[index] = (String) truck.getPlate();
            index++;
        }
        return a;
    }
}
