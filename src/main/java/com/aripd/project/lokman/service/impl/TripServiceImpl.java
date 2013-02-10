package com.aripd.project.lokman.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortDirection;
import com.aripd.common.dto.SortField;
import com.aripd.project.lokman.domain.Trip;
import com.aripd.project.lokman.repository.TripRepository;
import com.aripd.project.lokman.service.TripService;

@Service("tripService")
@Transactional
public class TripServiceImpl implements TripService {

	protected static Logger logger = Logger.getLogger(TripServiceImpl.class);
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private TripRepository repository;
	
	@Transactional(readOnly = true)
	public Trip getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	//public Page<Trip> findByNameStartsWith(@Param("name") String name, Pageable pageable) {
		//return null;
	//}
	
	@Transactional(readOnly = true)
	public List<Trip> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll(sortByLastNameAsc());
	}

	/**
	 * http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-six-sorting/
	 * http://static.springsource.org/spring-data/data-jpa/docs/1.0.x/reference/html/#jpa.query-methods.query-creation
	 * 
     * Returns a Sort object which sorts persons in ascending order by using the last name.
     * @return
     */
    private Sort sortByLastNameAsc() {
        return new Sort(Sort.Direction.DESC, "publishedAt");
    }
    
    @Transactional(readOnly = false)
    public Trip save(Trip trip) {
		return repository.save(trip);
	}
	
    @Transactional(readOnly = false)
	public Trip delete(Long id) {
		Trip trip = repository.findOne(id);
		repository.delete(id);
		return trip;
	}

	@Transactional(readOnly = true)
	public List<Trip> findByPublishedAt(DateTime publishedAt) {
		return repository.findByPublishedAt(publishedAt);
	}

	@Transactional(readOnly = true)
	@Override
	public ResultSet<Trip> getRecords(PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		String search = criteria.getSearch();
		List<SortField> sortFields = criteria.getSortFields();
		
		//Metamodel m = em.getMetamodel();
		//EntityType<Trip> Trip_ = m.entity(Trip.class);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> trip = cq.from(Trip.class);
        
        //Searching
        String remark = search;
        
        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate remarkPredicate;
        
        if ((remark != null) && (!(remark.isEmpty()))) {
        	remarkPredicate = cb.like(
                cb.upper(trip.<String>get("remark")), "%"+remark.toUpperCase()+"%");
            predicateList.add(remarkPredicate);
        }
     
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        //Predicate predicate = cb.equal(from.get(Trip_.remark), 12);
        cq.where(predicates);
        //cq.where(cb.like(from.get(Trip_.name), "*do"));
        
        //Sorting
        for (SortField sortField : sortFields) {
			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();
			if (direction.equalsIgnoreCase("asc"))
				cq.orderBy(cb.asc(trip.get(field)));
			else if (direction.equalsIgnoreCase("desc"))
				cq.orderBy(cb.desc(trip.get(field)));
		}
		
        //Pagination
        TypedQuery<Trip> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart/*filter.getOffset()*/);
        typedQuery = typedQuery.setMaxResults(displaySize/*filter.getMaxItemsPerPage()*/);
        List<Trip> resultList =  typedQuery.getResultList();
        
		//Query query = em.createQuery("from Trip as t where t.endingKm = ?1");
		//query.setParameter(1, 13);
		//Query query = em.createQuery("from Trip");
		//query.setFirstResult(displayStart);
		//query.setMaxResults(displaySize);
		
		//List<Trip> resultList = query.getResultList();
		
		List<Trip> resultAll = repository.findAll();
		Long totalRecords = (long) resultAll.size();
		
		return new ResultSet<Trip>(resultList, totalRecords, displaySize);
	}

}
