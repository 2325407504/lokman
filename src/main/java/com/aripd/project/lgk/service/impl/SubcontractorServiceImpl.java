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
import com.aripd.project.lgk.domain.Subcontractor;
import com.aripd.project.lgk.domain.Subcontractor_;
import com.aripd.project.lgk.repository.SubcontractorRepository;
import com.aripd.project.lgk.service.SubcontractorService;

@Service("subcontractorService")
@Transactional(readOnly = true)
public class SubcontractorServiceImpl implements SubcontractorService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SubcontractorRepository repository;
	
	public Subcontractor findOne(Long id) {
		return repository.findOne(id);
	}

	public Subcontractor findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	public List<Subcontractor> findAll() {
		return repository.findAll();
	}

	public List<Subcontractor> findByRegion(Region region) {
		return repository.findByRegion(region);
	}

	@Transactional
	public Subcontractor save(Subcontractor subcontractor) {
		return repository.save(subcontractor);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}

	@Transactional
	public void delete(Subcontractor subcontractor) {
		repository.delete(subcontractor);
	}

	@Override
	public ResultSet<Subcontractor> getRecords(PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		List<SortField> sortFields = criteria.getSortFields();
		String search = criteria.getSearch();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Subcontractor> cq = cb.createQuery(Subcontractor.class);
		Root<Subcontractor> root = cq.from(Subcontractor.class);

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if ((search != null) && (!(search.isEmpty()))) {
			Predicate predicate1 = cb.like(root.get(Subcontractor_.code), "%"+search+"%");
			Predicate predicate2 = cb.like(root.get(Subcontractor_.name), "%"+search+"%");
			Predicate predicate = cb.or(predicate1, predicate2);
			predicateList.add(predicate);
		}
		
		Predicate[] predicates = new Predicate[predicateList.size()];
		predicateList.toArray(predicates);
		cq.where(predicates);

		// Sorting
		for (SortField sortField : sortFields) {
			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();
			if (direction.equalsIgnoreCase("asc"))
				cq.orderBy(cb.asc(root.get(field)));
			else if (direction.equalsIgnoreCase("desc"))
				cq.orderBy(cb.desc(root.get(field)));
		}

		Long totalRecords = (long) em.createQuery(cq).getResultList().size();
		
		// Pagination
		TypedQuery<Subcontractor> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Subcontractor> resultList = typedQuery.getResultList();

		return new ResultSet<Subcontractor>(resultList, totalRecords, displaySize);
	}

}
