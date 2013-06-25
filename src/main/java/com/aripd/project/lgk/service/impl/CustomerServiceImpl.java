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
import com.aripd.project.lgk.domain.Customer;
import com.aripd.project.lgk.domain.Customer_;
import com.aripd.project.lgk.domain.Region;
import com.aripd.project.lgk.repository.CustomerRepository;
import com.aripd.project.lgk.service.CustomerService;

@Service("customerService")
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CustomerRepository repository;
	
	public Customer findOne(Long id) {
		return repository.findOne(id);
	}

	public Customer findOneByTaxNo(String taxNo) {
		return repository.findOneByTaxNo(taxNo);
	}

	public List<Customer> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Customer save(Customer customer) {
		return repository.save(customer);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}

	@Transactional
	public void delete(Customer customer) {
		repository.delete(customer);
	}

	public ResultSet<Customer> getRecords(PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		List<SortField> sortFields = criteria.getSortFields();
		String search = criteria.getSearch();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if ((search != null) && (!(search.isEmpty()))) {
			Predicate predicate1 = cb.like(root.get(Customer_.taxNo), "%"+search+"%");
			Predicate predicate2 = cb.like(root.get(Customer_.name), "%"+search+"%");
			Predicate predicate3 = cb.like(root.get(Customer_.phonenumber), "%"+search+"%");
			Predicate predicate = cb.or(predicate1, predicate2, predicate3);
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
		TypedQuery<Customer> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Customer> resultList = typedQuery.getResultList();

		return new ResultSet<Customer>(resultList, totalRecords, displaySize);
	}

}
