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
import javax.persistence.criteria.Join;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Account_;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesSortField;
import com.aripd.project.lgk.domain.Expense;
import com.aripd.project.lgk.domain.Expense_;
import com.aripd.project.lgk.report.expense.FillManager;
import com.aripd.project.lgk.report.expense.Layouter;
import com.aripd.project.lgk.report.expense.Writer;
import com.aripd.project.lgk.repository.ExpenseRepository;
import com.aripd.project.lgk.service.ExpenseService;
import com.aripd.project.lgk.service.ExpensetypeService;

@Service("expenseService")
@Transactional(readOnly = true)
public class ExpenseServiceImpl implements ExpenseService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ExpenseRepository repository;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "expensetypeService")
    private ExpensetypeService expensetypeService;

    public Expense findOne(Long id) {
        return repository.findOne(id);
    }

    public Expense findOneByAccountAndId(Account account, Long id) {
        return repository.findOneByAccountAndId(account, id);
    }

    public List<Expense> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);
        Root<Expense> root = cq.from(Expense.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Expense_.documentDate), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Expense> typedQuery = em.createQuery(cq);
        List<Expense> resultList = typedQuery.getResultList();

        return resultList;
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

    public DatatablesResultSet<Expense> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);
        Root<Expense> root = cq.from(Expense.class);
        Join<Expense, Account> account = root.join(Expense_.account);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Expense_.company), "%" + search + "%");
            Predicate predicate2 = cb.like(root.get(Expense_.description), "%" + search + "%");
            Predicate predicate3 = cb.like(account.get(Account_.username), "%" + search + "%");
            Predicate predicate = cb.or(predicate1, predicate2, predicate3);
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        for (DatatablesSortField sortField : sortFields) {
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
        TypedQuery<Expense> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Expense> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Expense>(resultList, totalRecords, displaySize);
    }

    public DatatablesResultSet<Expense> getRecords(Principal principal, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);
        Root<Expense> root = cq.from(Expense.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Expense_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Expense_.description), "%"
                    + search + "%");
            Predicate predicate = cb.and(predicate_, predicate1);
            predicateList.add(predicate);
        } else {
            predicateList.add(predicate_);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        for (DatatablesSortField sortField : sortFields) {
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
        TypedQuery<Expense> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Expense> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Expense>(resultList, totalRecords, displaySize);
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

        List<Expense> expenses = new ArrayList<Expense>();
        Expense expense;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String expensetype_code = row.getCell(1).getStringCellValue();
            Date documentDate = row.getCell(2).getDateCellValue();
            String company = row.getCell(3).getStringCellValue();
            String description = row.getCell(4).getStringCellValue();
            BigDecimal amount = new BigDecimal(row.getCell(5).getNumericCellValue(), MathContext.DECIMAL64);

            expense = new Expense();
            expense.setSubmitted(true);
            expense.setAccount(accountService.findOneByUsername(username));
            expense.setExpensetype(expensetypeService.findOneByCode(expensetype_code));
            expense.setDocumentDate(new DateTime(documentDate));
            expense.setCompany(company);
            expense.setDescription(description);
            expense.setAmount(amount);

            expenses.add(expense);
        }

        repository.save(expenses);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Expenses");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

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
