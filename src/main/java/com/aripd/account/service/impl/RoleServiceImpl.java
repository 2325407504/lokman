package com.aripd.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Role;
import com.aripd.account.domain.Role_;
import com.aripd.account.repository.RoleRepository;
import com.aripd.account.service.RoleService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;

@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private RoleRepository repository;

	public List<Role> findAll() {
		return repository.findAll();
	}

	public Role findOne(Long id) {
		return repository.findOne(id);
	}

	@Transactional
	public Role save(Role role) {
		return repository.save(role);
	}

	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public ResultSet<Role> getRecords(PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		String search = criteria.getSearch();
		List<SortField> sortFields = criteria.getSortFields();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Role> cq = cb.createQuery(Role.class);
		Root<Role> root = cq.from(Role.class);
		//Join<Role, Customer> root = cq.from(Role.class).join(Role_.customer);

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if ((search != null) && (!(search.isEmpty()))) {
			Predicate predicate1 = cb.like(root.get(Role_.name), "%"+search+"%");
			Predicate predicate2 = cb.like(root.get(Role_.code), "%"+search+"%");
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
		TypedQuery<Role> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Role> resultList = typedQuery.getResultList();

		return new ResultSet<Role>(resultList, totalRecords, displaySize);
	}

}
