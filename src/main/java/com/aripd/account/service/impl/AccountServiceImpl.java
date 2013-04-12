package com.aripd.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Account_;
import com.aripd.account.domain.Customer;
import com.aripd.account.domain.Customer_;
import com.aripd.account.repository.AccountRepository;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;

@Service("accountService")
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private AccountRepository repository;

	public List<Account> findAll() {
		return repository.findAll();
	}

	public Account findOne(Long id) {
		return repository.findOne(id);
	}

	public Account findOneByUsername(String username) {
		return repository.findOneByUsername(username);
	}

	@Transactional
	public Account save(Account account) {
		return repository.save(account);
	}

	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public ResultSet<Account> getRecords(PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		String search = criteria.getSearch();
		List<SortField> sortFields = criteria.getSortFields();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
		Root<Account> root = cq.from(Account.class);
		Join<Account, Customer> customer = root.join(Account_.customer);
		
		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if ((search != null) && (!(search.isEmpty()))) {
			Predicate predicate1 = cb.like(root.get(Account_.username), "%"+search+"%");
			Predicate predicate2 = cb.like(root.get(Account_.email), "%"+search+"%");
			Predicate predicate3 = cb.like(customer.get(Customer_.lastName), "%"+search+"%");
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
		TypedQuery<Account> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Account> resultList = typedQuery.getResultList();

		return new ResultSet<Account>(resultList, totalRecords, displaySize);
	}

}