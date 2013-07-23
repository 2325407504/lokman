package com.aripd.account.service.impl;

import com.aripd.account.domain.Memberlog;
import com.aripd.account.domain.Memberlog_;
import com.aripd.account.model.MemberlogFilterByIntervalForm;
import com.aripd.account.report.memberlog.FillManager;
import com.aripd.account.report.memberlog.Layouter;
import com.aripd.account.report.memberlog.Writer;
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
import com.aripd.account.repository.MemberlogRepository;
import com.aripd.account.service.MemberlogService;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.ProductService;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;

@Service("memberlogService")
@Transactional(readOnly = true)
public class MemberlogServiceImpl implements MemberlogService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MemberlogRepository repository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ProductService productService;

    public Memberlog findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Memberlog> findAll() {
        return repository.findAll();
    }

    public List<Memberlog> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Memberlog> cq = cb.createQuery(Memberlog.class);
        Root<Memberlog> root = cq.from(Memberlog.class);

        Predicate predicate = cb.between(root.get(Memberlog_.createdAt), startingTime, endingTime);

        cq.where(predicate);

        TypedQuery<Memberlog> typedQuery = em.createQuery(cq);
        List<Memberlog> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Memberlog save(Memberlog memberlog) {
        return repository.save(memberlog);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Memberlog memberlog) {
        repository.delete(memberlog);
    }

    @Override
    public DatatablesResultSet<Memberlog> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        String search = criteria.getSearch();
        List<DatatablesSortField> sortFields = criteria.getSortFields();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Memberlog> cq = cb.createQuery(Memberlog.class);
        Root<Memberlog> memberlog = cq.from(Memberlog.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.equal(memberlog.get(Memberlog_.sessionId), search);
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        /*for (DatatablesSortField sortField : sortFields) {
         String field = sortField.getField();
         String direction = sortField.getDirection().getDirection();
         if (direction.equalsIgnoreCase("asc")) {
         cq.orderBy(cb.asc(memberlog.get(field)));
         } else if (direction.equalsIgnoreCase("desc")) {
         cq.orderBy(cb.desc(memberlog.get(field)));
         }
         }*/

        Long totalRecords = (long) em.createQuery(cq).getResultList().size();

        // Pagination
        TypedQuery<Memberlog> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Memberlog> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Memberlog>(resultList, totalRecords, displaySize);
    }

    public void export(HttpServletResponse response, MemberlogFilterByIntervalForm formData) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Memberlog Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(formData.getStartingTime(), formData.getEndingTime()));

        // 6. Set the response properties
        String fileName = "MemberlogReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
