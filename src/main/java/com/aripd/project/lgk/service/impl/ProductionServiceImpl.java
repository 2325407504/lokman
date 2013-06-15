package com.aripd.project.lgk.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.domain.Production_;
import com.aripd.project.lgk.domain.Shift;
import com.aripd.project.lgk.report.production.FillManager;
import com.aripd.project.lgk.report.production.Layouter;
import com.aripd.project.lgk.report.production.Writer;
import com.aripd.project.lgk.repository.ProductionRepository;
import com.aripd.project.lgk.repository.BigbagRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.ShiftService;

@Service("productionService")
@Transactional(readOnly = true)
public class ProductionServiceImpl implements ProductionService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ProductionRepository repository;
    @Autowired
    private BigbagRepository bigbagRepository;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "shiftService")
    private ShiftService shiftService;

    public Production findOne(Long id) {
        return repository.findOne(id);
    }

    public Production findOneByAccountAndId(Account account, Long id) {
        return repository.findOneByAccountAndId(account, id);
    }

    public Production findOneByShiftdateAndShift(DateTime shiftdate, Shift shift) {
        return repository.findOneByShiftdateAndShift(shiftdate, shift);
    }

    @Transactional
    public Production save(Production production) {
        return repository.save(production);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Production production) {
        repository.delete(production);
    }

    public ResultSet<Production> getRecords(PagingCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<SortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Production> cq = cb.createQuery(Production.class);
        Root<Production> root = cq.from(Production.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Production_.remark), "%" + search + "%");
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
        TypedQuery<Production> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Production> resultList = typedQuery.getResultList();

        return new ResultSet<Production>(resultList, totalRecords, displaySize);
    }

    public ResultSet<Production> getRecords(Principal principal, PagingCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<SortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Production> cq = cb.createQuery(Production.class);
        Root<Production> root = cq.from(Production.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Production_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Production_.remark), "%" + search + "%");
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
        TypedQuery<Production> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Production> resultList = typedQuery.getResultList();

        return new ResultSet<Production>(resultList, totalRecords, displaySize);
    }

    public void exportXLS(HttpServletResponse response) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Production Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, repository.findAll());

        // 6. Set the response properties
        String fileName = "ProductionReport.xls";
        response.setHeader("Content-Disposition", "inline; filename="
                + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);

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

        List<Production> productions = new ArrayList<Production>();
        Production production;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            Date shiftdate = row.getCell(1).getDateCellValue();
            String shiftCode = row.getCell(2).getStringCellValue();
            Double feed = row.getCell(3).getNumericCellValue();
            String remark = row.getCell(4).getStringCellValue();

            production = new Production();
            production.setSubmitted(true);
            production.setAccount(accountService.findOneByUsername(username));
            production.setShiftdate(new DateTime(shiftdate));
            production.setShift(shiftService.findOneByCode(shiftCode));
            production.setFeed(feed);
            production.setRemark(remark);

            productions.add(production);
        }

        repository.save(productions);
    }

    @Override
    public void importCSV(String content) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");

        List<Production> productions = new ArrayList<Production>();
        Production production;

        String rows[] = content.split("\\r?\\n");
        for (String row : rows) {
            String column[] = row.split(",");

            String username = column[0];
            DateTime shiftdate = formatter.parseDateTime(column[1]);
            String shiftCode = column[2];
            Double feed = new Double(column[3]);
            String remark = column[4];

            production = new Production();
            production.setSubmitted(true);
            production.setAccount(accountService.findOneByUsername(username));
            production.setShiftdate(new DateTime(shiftdate));
            production.setShift(shiftService.findOneByCode(shiftCode));
            production.setFeed(feed);
            production.setRemark(remark);

            productions.add(production);
        }

        repository.save(productions);
    }
}