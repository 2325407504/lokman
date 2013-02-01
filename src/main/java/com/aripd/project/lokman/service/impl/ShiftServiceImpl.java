package com.aripd.project.lokman.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lokman.domain.Shift;
import com.aripd.project.lokman.repository.ShiftRepository;
import com.aripd.project.lokman.service.ShiftService;

@Service("shiftService")
@Transactional
public class ShiftServiceImpl implements ShiftService {

	protected static Logger logger = Logger.getLogger(ShiftServiceImpl.class);
	
	@Autowired
	private ShiftRepository repository;
	
	public Shift getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	//public Page<Shift> findByNameStartsWith(@Param("name") String name, Pageable pageable) {
		//return null;
	//}
	
	public List<Shift> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll(sortByStartedAtDesc());
	}

	/**
	 * http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-six-sorting/
	 * http://static.springsource.org/spring-data/data-jpa/docs/1.0.x/reference/html/#jpa.query-methods.query-creation
	 * 
     * Returns a Sort object which sorts persons in ascending order by using the last name.
     * @return
     */
    private Sort sortByStartedAtDesc() {
        return new Sort(Sort.Direction.DESC, "startedAt");
    }
    
	public Shift save(Shift shift) {
		return repository.save(shift);
	}
	
	public Shift delete(Long id) {
		Shift shift = repository.findOne(id);
		repository.delete(id);
		return shift;
	}

}
