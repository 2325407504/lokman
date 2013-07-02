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
import com.aripd.project.lgk.domain.Quota;
import com.aripd.project.lgk.domain.Quota_;
import com.aripd.project.lgk.repository.QuotaRepository;
import com.aripd.project.lgk.service.QuotaService;

@Service("quotaService")
@Transactional(readOnly = true)
public class QuotaServiceImpl implements QuotaService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private QuotaRepository repository;
	
	public Quota findOne(Long id) {
		return repository.findOne(id);
	}

	public Quota findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	public List<Quota> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Quota save(Quota quota) {
		return repository.save(quota);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}

	@Transactional
	public void delete(Quota quota) {
		repository.delete(quota);
	}

	public DatatablesResultSet<Quota> getRecords(DatatablesCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		List<DatatablesSortField> sortFields = criteria.getSortFields();
		String search = criteria.getSearch();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Quota> cq = cb.createQuery(Quota.class);
		Root<Quota> root = cq.from(Quota.class);

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if ((search != null) && (!(search.isEmpty()))) {
			Predicate predicate1 = cb.like(root.get(Quota_.code), "%"+search+"%");
			Predicate predicate2 = cb.like(root.get(Quota_.name), "%"+search+"%");
			Predicate predicate = cb.or(predicate1, predicate2);
			predicateList.add(predicate);
		}
		
		Predicate[] predicates = new Predicate[predicateList.size()];
		predicateList.toArray(predicates);
		cq.where(predicates);

		// Sorting
		for (DatatablesSortField sortField : sortFields) {
			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();
			if (direction.equalsIgnoreCase("asc"))
				cq.orderBy(cb.asc(root.get(field)));
			else if (direction.equalsIgnoreCase("desc"))
				cq.orderBy(cb.desc(root.get(field)));
		}

		Long totalRecords = (long) em.createQuery(cq).getResultList().size();
		
		// Pagination
		TypedQuery<Quota> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Quota> resultList = typedQuery.getResultList();

		return new DatatablesResultSet<Quota>(resultList, totalRecords, displaySize);
	}

}
