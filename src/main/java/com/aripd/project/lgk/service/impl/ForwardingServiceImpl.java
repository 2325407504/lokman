package com.aripd.project.lgk.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Forwarding_;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Forwarding_;
import com.aripd.project.lgk.report.forwarding.FillManager;
import com.aripd.project.lgk.report.forwarding.Layouter;
import com.aripd.project.lgk.report.forwarding.Writer;
import com.aripd.project.lgk.repository.ForwardingRepository;
import com.aripd.project.lgk.repository.UatfRepository;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.SubcontractorService;

@Service("forwardingService")
@Transactional(readOnly = true)
public class ForwardingServiceImpl implements ForwardingService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ForwardingRepository repository;
    @Autowired
    private UatfRepository uatfRepository;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "subcontractorService")
    private SubcontractorService subcontractorService;
    @Resource(name = "quotaService")
    private QuotaService quotaService;

    public Forwarding findOne(Long id) {
        return repository.findOne(id);
    }

    public Forwarding findOneByWaybillNo(String waybillNo) {
        return repository.findOneByWaybillNo(waybillNo);
    }

    public Forwarding findOneByAccountAndId(Account account, Long id) {
        return repository.findOneByAccountAndId(account, id);
    }

    public List<Forwarding> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Forwarding> cq = cb.createQuery(Forwarding.class);
        Root<Forwarding> root = cq.from(Forwarding.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Forwarding_.startingTime), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Forwarding> typedQuery = em.createQuery(cq);
        List<Forwarding> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Forwarding save(Forwarding forwarding) {
        return repository.save(forwarding);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Forwarding forwarding) {
        repository.delete(forwarding);
    }

    public ResultSet<Forwarding> getRecords(PagingCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<SortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Forwarding> cq = cb.createQuery(Forwarding.class);
        Root<Forwarding> root = cq.from(Forwarding.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Forwarding_.waybillNo), "%" + search + "%");
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        for (SortField sortField : sortFields) {
            String field = sortField.getField();
            String direction = sortField.getDirection().getDirection();
            if (direction.equalsIgnoreCase("asc")) {
                cq.orderBy(cb.asc(root.get(field)));
            } else if (direction.equalsIgnoreCase("desc")) {
                cq.orderBy(cb.desc(root.get(field)));
            }
        }

        Long totalRecords = (long) em.createQuery(cq).getResultList().size();

        // Pagination
        TypedQuery<Forwarding> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Forwarding> resultList = typedQuery.getResultList();

        return new ResultSet<Forwarding>(resultList, totalRecords, displaySize);
    }

    public ResultSet<Forwarding> getRecords(Principal principal, PagingCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<SortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Forwarding> cq = cb.createQuery(Forwarding.class);
        Root<Forwarding> root = cq.from(Forwarding.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Forwarding_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Forwarding_.waybillNo), "%" + search + "%");
            Predicate predicate = cb.and(predicate_, predicate1);
            predicateList.add(predicate);
        } else {
            predicateList.add(predicate_);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        for (SortField sortField : sortFields) {
            String field = sortField.getField();
            String direction = sortField.getDirection().getDirection();
            if (direction.equalsIgnoreCase("asc")) {
                cq.orderBy(cb.asc(root.get(field)));
            } else if (direction.equalsIgnoreCase("desc")) {
                cq.orderBy(cb.desc(root.get(field)));
            }
        }

        Long totalRecords = (long) em.createQuery(cq).getResultList().size();

        // Pagination
        TypedQuery<Forwarding> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Forwarding> resultList = typedQuery.getResultList();

        return new ResultSet<Forwarding>(resultList, totalRecords, displaySize);
    }

    public void importXLSX(String fileName) {
        InputStream iStream = null;
        try {
            iStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(iStream);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet worksheet = workbook.getSheetAt(0);
        Iterator<Row> rows = worksheet.rowIterator();

        List<Forwarding> forwardings = new ArrayList<Forwarding>();
        Forwarding forwarding;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String waybillNo = row.getCell(1).getStringCellValue();
            String driver = row.getCell(2).getStringCellValue();
            String plate = row.getCell(3).getStringCellValue();
            Date startingTime = row.getCell(4).getDateCellValue();
            Date endingTime = row.getCell(5).getDateCellValue();
            String endingPoint = row.getCell(6).getStringCellValue();
            Integer loadWeightInTonne = (int) row.getCell(7).getNumericCellValue();
            BigDecimal shippingCost = new BigDecimal(row.getCell(8).getNumericCellValue());
            String subcontractorCode = row.getCell(9).getStringCellValue();
            String quotaCode = row.getCell(10).getStringCellValue();

            forwarding = new Forwarding();
            forwarding.setSubmitted(true);
            forwarding.setAccount(accountService.findOneByUsername(username));
            forwarding.setWaybillNo(waybillNo);
            forwarding.setDriver(driver);
            forwarding.setPlate(plate);
            forwarding.setStartingTime(new DateTime(startingTime));
            forwarding.setEndingTime(new DateTime(endingTime));
            forwarding.setEndingPoint(endingPoint);
            forwarding.setLoadWeightInTonne(loadWeightInTonne);
            forwarding.setShippingCost(shippingCost);
            forwarding.setSubcontractor(subcontractorService.findOneByCode(subcontractorCode));
            forwarding.setQuota(quotaService.findOneByCode(quotaCode));

            forwardings.add(forwarding);
        }

        repository.save(forwardings);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Forwarding Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

        // 6. Set the response properties
        String fileName = "ForwardingReport.xls";
        response.setHeader("Content-Disposition", "inline; filename="
                + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);

    }
}
