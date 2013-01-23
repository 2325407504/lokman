package com.aripd.project.lokman.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lokman.domain.FMS;
import com.aripd.project.lokman.repository.FMSRepository;

@Service("fmsService")
@Transactional
public class FMSService {

	protected static Logger logger4J = Logger.getLogger(FMSService.class);
	
	@Autowired
	private FMSRepository repository;
	
	public FMS getOne(Long id) {
		logger4J.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	//public Page<FMS> findByNameStartsWith(@Param("name") String name, Pageable pageable) {
		//return null;
	//}
	
	public List<FMS> getAll() {
		logger4J.debug("Retrieving all persons");
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
    
	public Boolean saveOne(FMS fms) {
		FMS saved = repository.save(fms);
		if (saved == null)
			return false;

		logger4J.debug("Adding new person");
		return true;
	}
	
	public void deleteOne(Long id) {
		repository.delete(repository.findOne(id));
	}

	public Boolean deleteOne(FMS fms) {
		FMS existing = repository.findOne(fms.getId());
		if (existing == null)
			return false;

		repository.delete(existing);
		FMS deleted = repository.findOne(fms.getId());
		if (deleted != null)
			return false;

		logger4J.debug("Deleting existing person");
		return true;
	}

}
