package com.aripd.project.lgk.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesSortField;
import com.aripd.project.lgk.domain.Invoice;
import com.aripd.project.lgk.domain.Invoice_;
import com.aripd.project.lgk.report.invoice.FillManager;
import com.aripd.project.lgk.report.invoice.Layouter;
import com.aripd.project.lgk.report.invoice.Writer;
import com.aripd.project.lgk.repository.InvoiceRepository;
import com.aripd.project.lgk.repository.UatfRepository;
import com.aripd.project.lgk.service.InvoiceService;
import org.springframework.web.multipart.MultipartFile;

@Service("invoiceService")
@Transactional(readOnly = true)
public class InvoiceServiceImpl implements InvoiceService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private InvoiceRepository repository;
    @Autowired
    private UatfRepository uatfRepository;
    @Resource(name = "accountService")
    private AccountService accountService;

    public Invoice findOne(Long id) {
        return repository.findOne(id);
    }

    public Invoice findOneByDocumentNo(String documentNo) {
        return repository.findOneByDocumentNo(documentNo);
    }

    public List<Invoice> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> root = cq.from(Invoice.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Invoice_.documentDate), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Invoice> typedQuery = em.createQuery(cq);
        List<Invoice> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Invoice save(Invoice invoice) {
        return repository.save(invoice);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Invoice invoice) {
        repository.delete(invoice);
    }

    public DatatablesResultSet<Invoice> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> root = cq.from(Invoice.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Invoice_.documentNo), "%" + search + "%");
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
        TypedQuery<Invoice> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Invoice> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Invoice>(resultList, totalRecords, displaySize);
    }

    public DatatablesResultSet<Invoice> getRecords(Principal principal, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> root = cq.from(Invoice.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Invoice_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Invoice_.documentNo), "%" + search + "%");
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
        TypedQuery<Invoice> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Invoice> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Invoice>(resultList, totalRecords, displaySize);
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

        List<Invoice> invoices = new ArrayList<Invoice>();
        Invoice invoice;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            Date documentDate = row.getCell(1).getDateCellValue();
            String documentNo = row.getCell(2).getStringCellValue();
            String company = row.getCell(3).getStringCellValue();
            String driver = row.getCell(4).getStringCellValue();
            String plate = row.getCell(5).getStringCellValue();
            String invoiceCompany = row.getCell(6).getStringCellValue();
            String invoiceNo = row.getCell(7).getStringCellValue();
            Date invoiceDate = row.getCell(8).getDateCellValue();
            BigDecimal invoiceAmount = new BigDecimal(row.getCell(9).getNumericCellValue());

            invoice = new Invoice();
            /*
             invoice.setSubmitted(true);
             invoice.setAccount(accountService.findOneByUsername(username));
             invoice.setDocumentDate(new DateTime(documentDate));
             invoice.setDocumentNo(documentNo);
             invoice.setCompany(company);
             invoice.setDriver(driver);
             invoice.setPlate(plate);
             invoice.setInvoiceCompany(invoiceCompany);
             invoice.setInvoiceNo(invoiceNo);
             invoice.setInvoiceDate(new DateTime(invoiceDate));
             invoice.setInvoiceAmount(invoiceAmount);
             */
            invoices.add(invoice);
        }

        repository.save(invoices);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Invoice Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

        // 6. Set the response properties
        String fileName = "InvoiceReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
