package com.aripd.project.lgk.service.impl;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Account_;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesSortField;
import com.aripd.project.lgk.domain.Employeeleave;
import com.aripd.project.lgk.domain.Employeeleave_;
import com.aripd.project.lgk.report.employeeleave.FillManager;
import com.aripd.project.lgk.report.employeeleave.Layouter;
import com.aripd.project.lgk.report.employeeleave.Writer;
import com.aripd.project.lgk.repository.EmployeeleaveRepository;
import com.aripd.project.lgk.service.EmployeeleaveService;
import com.aripd.project.lgk.service.EmployeeleavetypeService;
import org.joda.time.DateMidnight;
import org.springframework.web.multipart.MultipartFile;

@Service("employeeleaveService")
@Transactional(readOnly = true)
public class EmployeeleaveServiceImpl implements EmployeeleaveService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EmployeeleaveRepository repository;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "employeeleavetypeService")
    private EmployeeleavetypeService employeeleavetypeService;

    public Employeeleave findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Employeeleave> findByInterval(Date starting, Date ending, Long account_id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeleave> cq = cb.createQuery(Employeeleave.class);
        Root<Employeeleave> root = cq.from(Employeeleave.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Employeeleave_.startingDate), starting, ending);
        Predicate predicate2 = null;
        if (account_id != null) {
            Join<Employeeleave, Account> account = root.join(Employeeleave_.account);
            predicate2 = cb.equal(account.get(Account_.id), account_id);
        }

        Predicate predicate = predicate1;
        if (predicate2 != null) {
            predicate = cb.and(predicate1, predicate2);
        }

        predicateList.add(predicate);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Employeeleave> typedQuery = em.createQuery(cq);
        List<Employeeleave> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Employeeleave save(Employeeleave leave) {
        return repository.save(leave);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Employeeleave leave) {
        repository.delete(leave);
    }

    public DatatablesResultSet<Employeeleave> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeleave> cq = cb.createQuery(Employeeleave.class);
        Root<Employeeleave> root = cq.from(Employeeleave.class);
        Join<Employeeleave, Account> account = root.join(Employeeleave_.account);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(account.get(Account_.username), "%" + search + "%");
            Predicate predicate2 = cb.like(root.get(Employeeleave_.remark), "%" + search + "%");
            Predicate predicate = cb.or(predicate1, predicate2);
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
        TypedQuery<Employeeleave> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Employeeleave> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Employeeleave>(resultList, totalRecords, displaySize);
    }

    public DatatablesResultSet<Employeeleave> getRecords(Principal principal, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeleave> cq = cb.createQuery(Employeeleave.class);
        Root<Employeeleave> root = cq.from(Employeeleave.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Employeeleave_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Employeeleave_.remark), "%" + search + "%");
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
        TypedQuery<Employeeleave> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Employeeleave> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Employeeleave>(resultList, totalRecords, displaySize);
    }

    public void importData(MultipartFile file) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet worksheet = workbook.getSheetAt(0);
        Iterator<Row> rows = worksheet.rowIterator();

        List<Employeeleave> leaves = new ArrayList<Employeeleave>();
        Employeeleave employeeleave;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String leavetype_code = row.getCell(1).getStringCellValue();
            Date startingDate = row.getCell(2).getDateCellValue();
            Date endingDate = row.getCell(3).getDateCellValue();
            String remark = row.getCell(4).getStringCellValue();

            employeeleave = new Employeeleave();
            employeeleave.setSubmitted(true);
            employeeleave.setAccount(accountService.findOneByUsername(username));
            employeeleave.setEmployeeleavetype(employeeleavetypeService.findOneByCode(leavetype_code));
            employeeleave.setStartingDate(startingDate);
            employeeleave.setEndingDate(endingDate);
            employeeleave.setRemark(remark);

            leaves.add(employeeleave);
        }

        repository.save(leaves);
    }

    public void exportByInterval(HttpServletResponse response, Date starting, Date ending, Long account_id) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Employee Leaves");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(starting, ending, account_id));

        // 6. Set the response properties
        String fileName = "EmployeeLeaveReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }

    public List<Employeeleave> getLeaveTotal(Integer year, Long account_id) {
        DateMidnight startingDateTime = new DateMidnight().withYear(year).withDayOfYear(1);
        DateMidnight endingDateTime = startingDateTime.plusYears(1);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeleave> cq = cb.createQuery(Employeeleave.class);
        Root<Employeeleave> root = cq.from(Employeeleave.class);
        Join<Employeeleave, Account> account = root.join(Employeeleave_.account);

        Predicate predicate1 = cb.between(root.get(Employeeleave_.startingDate), startingDateTime.toDate(), endingDateTime.toDate());
        Predicate predicate2 = cb.equal(account.get(Account_.id), account_id);
        Predicate predicate = cb.and(predicate1, predicate2);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        predicateList.add(predicate);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Employeeleave> typedQuery = em.createQuery(cq);
        List<Employeeleave> resultList = typedQuery.getResultList();

        return resultList;
    }
}
