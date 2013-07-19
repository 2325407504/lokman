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
import com.aripd.project.lgk.domain.Employeeworkinghour;
import com.aripd.project.lgk.domain.Employeeworkinghour_;
import com.aripd.project.lgk.model.EmployeeworkinghourReportModel;
import com.aripd.project.lgk.report.employeeworkinghour.FillManager;
import com.aripd.project.lgk.report.employeeworkinghour.Layouter;
import com.aripd.project.lgk.report.employeeworkinghour.Writer;
import com.aripd.project.lgk.repository.EmployeeworkinghourRepository;
import com.aripd.project.lgk.service.EmployeeworkinghourService;
import com.aripd.project.lgk.service.EmployeeworkinghourtypeService;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.web.multipart.MultipartFile;

@Service("employeeworkinghourService")
@Transactional(readOnly = true)
public class EmployeeworkinghourServiceImpl implements EmployeeworkinghourService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EmployeeworkinghourRepository repository;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "employeeworkinghourtypeService")
    private EmployeeworkinghourtypeService employeeworkinghourtypeService;

    public Employeeworkinghour findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Employeeworkinghour> findByAccount(Account account) {
        return repository.findByAccount(account);
    }

    @Transactional
    public Employeeworkinghour save(Employeeworkinghour workinghour) {
        return repository.save(workinghour);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Employeeworkinghour workinghour) {
        repository.delete(workinghour);
    }

    public DatatablesResultSet<Employeeworkinghour> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeworkinghour> cq = cb.createQuery(Employeeworkinghour.class);
        Root<Employeeworkinghour> root = cq.from(Employeeworkinghour.class);
        Join<Employeeworkinghour, Account> account = root.join(Employeeworkinghour_.account);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(account.get(Account_.username), "%" + search + "%");
            Predicate predicate2 = cb.like(root.get(Employeeworkinghour_.remark), "%" + search + "%");
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
        TypedQuery<Employeeworkinghour> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Employeeworkinghour> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Employeeworkinghour>(resultList, totalRecords, displaySize);
    }

    public DatatablesResultSet<Employeeworkinghour> getRecords(Principal principal, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeworkinghour> cq = cb.createQuery(Employeeworkinghour.class);
        Root<Employeeworkinghour> root = cq.from(Employeeworkinghour.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Employeeworkinghour_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Employeeworkinghour_.remark), "%" + search + "%");
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
        TypedQuery<Employeeworkinghour> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Employeeworkinghour> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Employeeworkinghour>(resultList, totalRecords, displaySize);
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

        List<Employeeworkinghour> workinghours = new ArrayList<Employeeworkinghour>();
        Employeeworkinghour employeeworkinghour;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String workinghourtype_code = row.getCell(1).getStringCellValue();
            Date startingDateTime = row.getCell(2).getDateCellValue();
            Date endingDateTime = row.getCell(3).getDateCellValue();
            String remark = row.getCell(4).getStringCellValue();

            employeeworkinghour = new Employeeworkinghour();
            employeeworkinghour.setSubmitted(true);
            employeeworkinghour.setAccount(accountService.findOneByUsername(username));
            employeeworkinghour.setEmployeeworkinghourtype(employeeworkinghourtypeService.findOneByCode(workinghourtype_code));
            employeeworkinghour.setStartingDateTime(new DateTime(startingDateTime));
            employeeworkinghour.setEndingDateTime(new DateTime(endingDateTime));
            employeeworkinghour.setRemark(remark);

            workinghours.add(employeeworkinghour);
        }

        repository.save(workinghours);
    }

    public void exportByAccount(HttpServletResponse response, Account account) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Employee Working Hours for " + account.getUsername());

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.retrieveDatasource(account));

        // 6. Set the response properties
        String fileName = "EmployeeWorkingHourReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }

    public List<EmployeeworkinghourReportModel> retrieveDatasource(Account account) {
        Date employmentDate = account.getEmployee().getEmploymentDate();
        DateTime employmentDateTime = new DateTime(employmentDate);
        DateTime endDate = new DateTime();

        List<EmployeeworkinghourReportModel> ermList = new ArrayList<EmployeeworkinghourReportModel>();
        for (DateTime date = employmentDateTime; date.isBefore(endDate); date = date.plusYears(1)) {
            EmployeeworkinghourReportModel erm = new EmployeeworkinghourReportModel();
            erm.setDate(date.toDate());
            erm.setQualified(this.getAnnualLeaveQualified(account, date));
            erm.setUsed(this.getAnnualLeaveUsed(account, date, date.plusYears(1)));
            ermList.add(erm);
        }
        return ermList;
    }

    public int getAnnualLeaveQualified(Account account, DateTime dt1) {
        DateTime dt2 = new DateTime(account.getEmployee().getEmploymentDate());
        int diff = Years.yearsBetween(dt2, dt1).getYears();
        int workinghour = 0;
        if (1 <= diff && diff <= 5) {
            workinghour = 14;
        } else if (5 < diff && diff < 15) {
            workinghour = 20;
        } else if (15 <= diff) {
            workinghour = 26;
        }
        return workinghour;
    }

    public int getAnnualLeaveUsed(Account account, DateTime dt1, DateTime dt2) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeworkinghour> cq = cb.createQuery(Employeeworkinghour.class);
        Root<Employeeworkinghour> root = cq.from(Employeeworkinghour.class);

        Predicate predicate1 = cb.between(root.get(Employeeworkinghour_.startingDateTime), dt1, dt2);
        Predicate predicate2 = cb.equal(root.get(Employeeworkinghour_.account), account);
        Predicate predicate = cb.and(predicate1, predicate2);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        predicateList.add(predicate);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Employeeworkinghour> typedQuery = em.createQuery(cq);
        List<Employeeworkinghour> resultList = typedQuery.getResultList();

        int workinghour = 0;
        for (Employeeworkinghour employeeworkinghour : resultList) {
            workinghour += employeeworkinghour.getNofWorkhours();
        }
        return workinghour;
    }
}
