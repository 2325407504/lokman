package com.aripd.account.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.repository.AccountRepository;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;
import com.aripd.account.domain.Account_;

/**
 * This implementation of the AccountService interface communicates with the
 * database by using a Spring Data JPA repository.
 * 
 * @author aripd.com
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	protected static Logger logger = Logger.getLogger(AccountServiceImpl.class);

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private AccountRepository repository;

	public Account getOne(Long id) {
		logger.debug("Retrieving account based on id");
		return repository.findOne(id);
	}

	public Account getOneByUsername(String username) {
		logger.debug("Retrieving account based on username");
		return repository.findOneByUsername(username);
	}

	@PreAuthorize("isFullyAuthenticated()")
	public Account getActiveUser() {
		org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User) (SecurityContextHolder
				.getContext()).getAuthentication().getPrincipal();
		return getOneByUsername(securityUser.getUsername());
	}

	@PreAuthorize("isFullyAuthenticated()")
	public boolean hasUsername(String username) {
		List<Account> list = repository.findAll();
		list.remove(getActiveUser());

		Iterator<Account> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public List<Account> getAll() {
		logger.debug("Retrieving all accounts");
		return repository.findAll();
	}

	public Account save(Account formData) {
		return repository.save(formData);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Account> findAll() {
		logger.debug("Finding all accounts");
		return repository.findAll();
	}

	@Transactional(readOnly = true)
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

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if ((search != null) && (!(search.isEmpty()))) {
			//Predicate predicate = cb.equal(account.get(Account_.remark), search);
			Predicate predicate = cb.like(root.get(Account_.username), "%"+search+"%");
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