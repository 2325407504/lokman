package com.aripd.project.lokman.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lokman.domain.Trip;
import com.aripd.project.lokman.repository.TripRepository;
import com.aripd.project.lokman.service.TripService;

@Service("tripService")
@Transactional
public class TripServiceImpl implements TripService {

	protected static Logger logger = Logger.getLogger(TripServiceImpl.class);
	
	@Autowired
	private TripRepository repository;
	
	public Trip getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	//public Page<Trip> findByNameStartsWith(@Param("name") String name, Pageable pageable) {
		//return null;
	//}
	
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
    
	public Trip save(Trip trip) {
		return repository.save(trip);
	}
	
	public Trip delete(Long id) {
		Trip trip = repository.findOne(id);
		repository.delete(id);
		return trip;
	}

	public List<Trip> findByPublishedAt(DateTime publishedAt) {
		return repository.findByPublishedAt(publishedAt);
	}

	@Override
	public ResultSet<Trip> getRecords(PagingCriteria criteria) {
		List<Trip> rows = repository.findAll();
		Long totalRecords = 10L;
		Long totalDisplayRecords = 10L;
		return new ResultSet<Trip>(rows, totalRecords, totalDisplayRecords);
	}

}
