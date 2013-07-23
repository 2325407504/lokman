package com.aripd.project.lgk.service.impl;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Account_;
import com.aripd.account.domain.Employee;
import com.aripd.account.domain.Employee_;
import com.aripd.account.service.AccountService;
import com.aripd.account.service.EmployeeService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesSortField;
import com.aripd.project.lgk.domain.Payment;
import com.aripd.project.lgk.domain.Payment_;
import com.aripd.project.lgk.model.PaymentFilterByIntervalForm;
import com.aripd.project.lgk.report.payment.FillManager;
import com.aripd.project.lgk.report.payment.Layouter;
import com.aripd.project.lgk.report.payment.Writer;
import com.aripd.project.lgk.repository.PaymentRepository;
import com.aripd.project.lgk.service.PaymentService;
import com.aripd.project.lgk.service.PaymenttypeService;
import org.springframework.web.multipart.MultipartFile;

@Service("paymentService")
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private PaymentRepository repository;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "employeeService")
    private EmployeeService employeeService;
    @Resource(name = "paymenttypeService")
    private PaymenttypeService paymenttypeService;

    public Payment findOne(Long id) {
        return repository.findOne(id);
    }

    public Payment findOneByAccountAndId(Account account, Long id) {
        return repository.findOneByAccountAndId(account, id);
    }

    public List<Payment> findByInterval(Date starting, Date ending, Long employee_id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> root = cq.from(Payment.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Payment_.documentDate), starting, ending);
        Predicate predicate2 = null;
        if (employee_id != null) {
            Join<Payment, Employee> employee = root.join(Payment_.employee);
            predicate2 = cb.equal(employee.get(Employee_.id), employee_id);
        }

        Predicate predicate = predicate1;
        if (predicate2 != null) {
            predicate = cb.and(predicate1, predicate2);
        }

        predicateList.add(predicate);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Payment> typedQuery = em.createQuery(cq);
        List<Payment> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Payment payment) {
        repository.delete(payment);
    }

    public DatatablesResultSet<Payment> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> root = cq.from(Payment.class);
        Join<Payment, Account> account = root.join(Payment_.account);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(account.get(Account_.username), "%" + search + "%");
            Predicate predicate2 = cb.like(root.get(Payment_.remark), "%" + search + "%");
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
        TypedQuery<Payment> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Payment> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Payment>(resultList, totalRecords, displaySize);
    }

    public DatatablesResultSet<Payment> getRecords(Principal principal, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
        Root<Payment> root = cq.from(Payment.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Payment_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Payment_.remark), "%" + search + "%");
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
        TypedQuery<Payment> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Payment> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Payment>(resultList, totalRecords, displaySize);
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

        List<Payment> payments = new ArrayList<Payment>();
        Payment payment;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String tckimlikno = row.getCell(1).getStringCellValue();
            String paymenttype_code = row.getCell(2).getStringCellValue();
            Date documentDate = row.getCell(3).getDateCellValue();
            String company = row.getCell(4).getStringCellValue();
            String remark = row.getCell(5).getStringCellValue();
            BigDecimal amount = new BigDecimal(row.getCell(6).getNumericCellValue(), MathContext.DECIMAL64);

            payment = new Payment();
            payment.setAccount(accountService.findOneByUsername(username));
            payment.setEmployee(employeeService.findOneByTckimlikno(tckimlikno));
            payment.setPaymenttype(paymenttypeService.findOneByCode(paymenttype_code));
            payment.setDocumentDate(documentDate);
            payment.setRemark(remark);
            payment.setAmount(amount);

            payments.add(payment);
        }

        repository.save(payments);
    }

    public void export(HttpServletResponse response, PaymentFilterByIntervalForm formData) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Payments");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(formData.getStartingDate(), formData.getEndingDate(), formData.getEmployee().getId()));

        // 6. Set the response properties
        String fileName = "PaymentReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
