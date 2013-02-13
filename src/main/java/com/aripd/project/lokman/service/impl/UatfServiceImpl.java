package com.aripd.project.lokman.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;
import com.aripd.project.lokman.domain.Uatf;
import com.aripd.project.lokman.domain.Uatf_;
import com.aripd.project.lokman.report.uatf.FillManager;
import com.aripd.project.lokman.report.uatf.Layouter;
import com.aripd.project.lokman.report.uatf.Writer;
import com.aripd.project.lokman.repository.UatfRepository;
import com.aripd.project.lokman.service.ForwardingService;
import com.aripd.project.lokman.service.UatfService;

@Service("uatfService")
@Transactional
public class UatfServiceImpl implements UatfService {

	protected static Logger logger = Logger.getLogger(UatfServiceImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UatfRepository repository;
	
	@Autowired
	private ForwardingService forwardingService;
	
	@Transactional(readOnly = true)
	public Uatf getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<Uatf> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	@Transactional(readOnly = false)
	public Uatf save(Uatf uatf) {
		return repository.save(uatf);
	}
	
	@Transactional(readOnly = false)
	public Uatf delete(Long id) {
		Uatf uatf = repository.findOne(id);
		repository.delete(id);
		return uatf;
	}

	@Transactional(readOnly = true)
	public List<Uatf> getByForwardingId(Long id) {
		logger.debug("Retrieving records based on forwarding id");
		return repository.findByForwardingId(id);
	}

	@Transactional(readOnly = true)
	@Override
	public ResultSet<Uatf> getDataTables(Long forwarding_id, PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		String search = criteria.getSearch();
		List<SortField> sortFields = criteria.getSortFields();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Uatf> cq = cb.createQuery(Uatf.class);
		Root<Uatf> uatf = cq.from(Uatf.class);

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		Predicate filter1 = cb.equal(uatf.get(Uatf_.forwarding), forwardingService.getOne(forwarding_id));
		predicateList.add(filter1);
		
		if ((search != null) && (!(search.isEmpty()))) {
			Predicate predicate = cb.like(uatf.get(Uatf_.code), "%"+search+"%");
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
				cq.orderBy(cb.asc(uatf.get(field)));
			else if (direction.equalsIgnoreCase("desc"))
				cq.orderBy(cb.desc(uatf.get(field)));
		}

		Long totalRecords = (long) em.createQuery(cq).getResultList().size();
		
		// Pagination
		TypedQuery<Uatf> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Uatf> resultList = typedQuery.getResultList();

		return new ResultSet<Uatf>(resultList, totalRecords, displaySize);
	}

	public void exportXLS(HttpServletResponse response) {
		logger.debug("Exporting Excel report");

		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("UATF Report");

		// 3. Define starting indices for rows and columns
		int startRowIndex = 0;
		int startColIndex = 0;

		// 4. Build layout
		// Build title, date, and column headers
		Layouter.buildReport(worksheet, startRowIndex, startColIndex);

		// 5. Fill report
		FillManager.fillReport(worksheet, startRowIndex, startColIndex, repository.findAll());

		// 6. Set the response properties
		String fileName = "UATFReport.xls";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-excel");

		// 7. Write to the output stream
		Writer.write(response, worksheet);

	}

}
