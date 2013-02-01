package com.aripd.project.lokman.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lokman.domain.FMS;
import com.aripd.project.lokman.repository.FMSRepository;
import com.aripd.project.lokman.service.FMSService;

@Service("fmsService")
@Transactional
public class FMSServiceImpl implements FMSService {

	protected static Logger logger = Logger.getLogger(FMSServiceImpl.class);
	
	@Autowired
	private FMSRepository repository;
	
	public FMS getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	//public Page<FMS> findByNameStartsWith(@Param("name") String name, Pageable pageable) {
		//return null;
	//}
	
	public List<FMS> getAll() {
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
    
	public FMS save(FMS fms) {
		return repository.save(fms);
	}
	
	public FMS delete(Long id) {
		FMS fms = repository.findOne(id);
		repository.delete(id);
		return fms;
	}

	public List<FMS> findByPublishedAt(DateTime publishedAt) {
		return repository.findByPublishedAt(publishedAt);
	}

}
