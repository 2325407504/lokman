package com.aripd.project.lgk.service.impl;

import java.security.Principal;
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
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;
import com.aripd.project.lgk.domain.Expense;
import com.aripd.project.lgk.domain.Expense_;
import com.aripd.project.lgk.report.expense.FillManager;
import com.aripd.project.lgk.report.expense.Layouter;
import com.aripd.project.lgk.report.expense.Writer;
import com.aripd.project.lgk.repository.ExpenseRepository;
import com.aripd.project.lgk.service.ExpenseService;

@Service("expenseService")
@Transactional(readOnly = true)
public class ExpenseServiceImpl implements ExpenseService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ExpenseRepository repository;

	@Resource(name = "accountService")
	private AccountService accountService;
	
	public Expense findOne(Long id) {
		return repository.findOne(id);
	}

	public Expense findOneByAccountAndId(Account account, Long id) {
		return repository.findOneByAccountAndId(account, id);
	}

	@Transactional
	public Expense save(Expense expense) {
		return repository.save(expense);
	}

	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}

	@Transactional
	public void delete(Expense expense) {
		repository.delete(expense);
	}

	public ResultSet<Expense> getRecords(PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		List<SortField> sortFields = criteria.getSortFields();
		String search = criteria.getSearch();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);
		Root<Expense> root = cq.from(Expense.class);

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if ((search != null) && (!(search.isEmpty()))) {
			Predicate predicate = cb.like(root.get(Expense_.description), "%"+search+"%");
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
		TypedQuery<Expense> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Expense> resultList = typedQuery.getResultList();

		return new ResultSet<Expense>(resultList, totalRecords, displaySize);
	}

	public ResultSet<Expense> getRecords(Principal principal, PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		List<SortField> sortFields = criteria.getSortFields();
		String search = criteria.getSearch();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);
		Root<Expense> root = cq.from(Expense.class);

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		Account account = accountService.findOneByUsername(principal.getName());
		Predicate predicate_ = cb.equal(root.get(Expense_.account), account);
		
		if ((search != null) && (!(search.isEmpty()))) {
			Predicate predicate1 = cb.like(root.get(Expense_.description), "%"+search+"%");
			Predicate predicate = cb.and(predicate_, predicate1);
			predicateList.add(predicate);
		}
		else {
			predicateList.add(predicate_);
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
		TypedQuery<Expense> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Expense> resultList = typedQuery.getResultList();

		return new ResultSet<Expense>(resultList, totalRecords, displaySize);
	}

	public void exportXLS(HttpServletResponse response) {
		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("Expense Report");

		// 3. Define starting indices for rows and columns
		int startRowIndex = 0;
		int startColIndex = 0;

		// 4. Build layout
		// Build title, date, and column headers
		Layouter.buildReport(worksheet, startRowIndex, startColIndex);

		// 5. Fill report
		FillManager.fillReport(worksheet, startRowIndex, startColIndex, repository.findAll());

		// 6. Set the response properties
		String fileName = "ExpenseReport.xls";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-excel");

		// 7. Write to the output stream
		Writer.write(response, worksheet);

	}

}
