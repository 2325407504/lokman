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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;
import com.aripd.project.lokman.domain.Trip;
import com.aripd.project.lokman.domain.Trip_;
import com.aripd.project.lokman.report.trip.FillManager;
import com.aripd.project.lokman.report.trip.Layouter;
import com.aripd.project.lokman.report.trip.Writer;
import com.aripd.project.lokman.repository.TripRepository;
import com.aripd.project.lokman.service.TripService;

@Service("tripService")
@Transactional(readOnly = true)
public class TripServiceImpl implements TripService {

	protected static Logger logger = Logger.getLogger(TripServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private TripRepository repository;

	public Trip getOne(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<Trip> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll(sortByIdDesc());
	}

	/**
	 * http://www.petrikainulainen.net/programming/spring-framework/spring-data-
	 * jpa-tutorial-part-six-sorting/
	 * http://static.springsource.org/spring-data/
	 * data-jpa/docs/1.0.x/reference/html/#jpa.query-methods.query-creation
	 * 
	 * Returns a Sort object which sorts persons in ascending order by using the
	 * last name.
	 * 
	 * @return
	 */
	private Sort sortByIdDesc() {
		return new Sort(Sort.Direction.DESC, "id");
	}

	@Transactional(readOnly = false)
	public Trip save(Trip trip) {
		return repository.save(trip);
	}
	
	public void saveOrUpdate(Trip trip) {
		if (trip.getId() == null) {
			logger.debug("Saving");
			em.persist(trip);
		}
		else {
			logger.debug("Updating");
			em.merge(trip);
		}
	}

	@Transactional(readOnly = false)
	public Trip delete(Long id) {
		Trip trip = repository.findOne(id);
		repository.delete(id);
		return trip;
	}

	@Override
	public ResultSet<Trip> getRecords(PagingCriteria criteria) {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		Integer pageNumber = criteria.getPageNumber();
		String search = criteria.getSearch();
		List<SortField> sortFields = criteria.getSortFields();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
		Root<Trip> root = cq.from(Trip.class);

		// Filtering and Searching
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if ((search != null) && (!(search.isEmpty()))) {
			//Predicate predicate = cb.equal(trip.get(Trip_.remark), search);
			Predicate predicate = cb.like(root.get(Trip_.remark), "%"+search+"%");
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
		TypedQuery<Trip> typedQuery = em.createQuery(cq);
		typedQuery = typedQuery.setFirstResult(displayStart);
		typedQuery = typedQuery.setMaxResults(displaySize);
		List<Trip> resultList = typedQuery.getResultList();

		return new ResultSet<Trip>(resultList, totalRecords, displaySize);
	}

	/**
	 * Processes Apache POI-based reports
	 * Exports for Excel format. It does the following steps:
	 * 
	 * <pre>
	 * 1. Create new workbook
	 * 2. Create new worksheet
	 * 3. Define starting indices for rows and columns
	 * 4. Build layout 
	 * 5. Fill report
	 * 6. Set the HttpServletResponse properties
	 * 7. Write to the output stream
	 * </pre>
	 */
	public void exportXLS(HttpServletResponse response) {
		logger.debug("Exporting Excel report");

		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("Trip Report");

		// 3. Define starting indices for rows and columns
		int startRowIndex = 0;
		int startColIndex = 0;

		// 4. Build layout
		// Build title, date, and column headers
		Layouter.buildReport(worksheet, startRowIndex, startColIndex);

		// 5. Fill report
		FillManager.fillReport(worksheet, startRowIndex, startColIndex, repository.findAll());

		// 6. Set the response properties
		String fileName = "TripReport.xls";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-excel");

		// 7. Write to the output stream
		Writer.write(response, worksheet);

	}

}
