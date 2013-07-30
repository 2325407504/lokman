package com.aripd.project.lgk.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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

import com.aripd.member.domain.Member;
import com.aripd.common.dto.autocomplete.AutocompleteCriteria;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesSortField;
import com.aripd.project.lgk.domain.Waybill;
import com.aripd.project.lgk.domain.Waybill_;
import com.aripd.project.lgk.report.waybill.FillManager;
import com.aripd.project.lgk.report.waybill.Layouter;
import com.aripd.project.lgk.report.waybill.Writer;
import com.aripd.project.lgk.repository.WaybillRepository;
import com.aripd.project.lgk.service.InvoiceService;
import com.aripd.project.lgk.service.WaybillService;
import org.springframework.web.multipart.MultipartFile;

@Service("waybillService")
@Transactional(readOnly = true)
public class WaybillServiceImpl implements WaybillService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private WaybillRepository repository;
    @Resource(name = "invoiceService")
    private InvoiceService invoiceService;

    public Waybill findOne(Long id) {
        return repository.findOne(id);
    }

    public Waybill findOneByDocumentNo(String documentNo) {
        return repository.findOneByDocumentNo(documentNo);
    }

    public Waybill findOneByMemberAndId(Member member, Long id) {
        return repository.findOneByMemberAndId(member, id);
    }

    public List<Waybill> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Waybill> cq = cb.createQuery(Waybill.class);
        Root<Waybill> root = cq.from(Waybill.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Waybill_.documentDate), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Waybill> typedQuery = em.createQuery(cq);
        List<Waybill> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Waybill save(Waybill waybill) {
        return repository.save(waybill);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Waybill waybill) {
        repository.delete(waybill);
    }

    public DatatablesResultSet<Waybill> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        String search = criteria.getSearch();
        List<DatatablesSortField> sortFields = criteria.getSortFields();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Waybill> cq = cb.createQuery(Waybill.class);
        Root<Waybill> root = cq.from(Waybill.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Waybill_.documentNo), "%" + search + "%");
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
        TypedQuery<Waybill> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Waybill> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Waybill>(resultList, totalRecords, displaySize);
    }

    public DatatablesResultSet<Waybill> getRecords(Long invoice_id, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        String search = criteria.getSearch();
        List<DatatablesSortField> sortFields = criteria.getSortFields();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Waybill> cq = cb.createQuery(Waybill.class);
        Root<Waybill> root = cq.from(Waybill.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Predicate filter1 = cb.equal(root.get(Waybill_.invoice), invoiceService.findOne(invoice_id));
        predicateList.add(filter1);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Waybill_.documentNo), "%" + search + "%");
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
        TypedQuery<Waybill> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Waybill> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Waybill>(resultList, totalRecords, displaySize);
    }

    public List<String> getRecords(AutocompleteCriteria criteria) {
        String search = criteria.getTerm();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Waybill> cq = cb.createQuery(Waybill.class);
        Root<Waybill> root = cq.from(Waybill.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Waybill_.documentNo), "%" + search + "%");
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);
        cq.orderBy(cb.asc(root.get(Waybill_.documentNo)));

        TypedQuery<Waybill> typedQuery = em.createQuery(cq);
        List<Waybill> resultList = typedQuery.getResultList();

        //return new AutocompleteResultSet<Waybill>(resultList);

        List<String> result = new ArrayList<String>();
        for (Waybill waybill : resultList) {
            result.add(waybill.getDocumentNo());
        }

        return result;
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

        List<Waybill> waybills = new ArrayList<Waybill>();
        Waybill waybill;

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

            waybill = new Waybill();
            /*
             waybill.setSubmitted(true);
             waybill.setMember(memberService.findOneByUsername(username));
             waybill.setDocumentDate(new DateTime(documentDate));
             waybill.setDocumentNo(documentNo);
             waybill.setCompany(company);
             waybill.setDriver(driver);
             waybill.setPlate(plate);
             waybill.setInvoiceCompany(invoiceCompany);
             waybill.setInvoiceNo(invoiceNo);
             waybill.setInvoiceDate(new DateTime(invoiceDate));
             waybill.setInvoiceAmount(invoiceAmount);
             */
            waybills.add(waybill);
        }

        repository.save(waybills);
    }

    public void export(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Waybill Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

        // 6. Set the response properties
        String fileName = "WaybillReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
