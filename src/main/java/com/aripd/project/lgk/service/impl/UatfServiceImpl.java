package com.aripd.project.lgk.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesSortField;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Forwarding_;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.domain.Uatf_;
import com.aripd.project.lgk.report.uatf.FillManager;
import com.aripd.project.lgk.report.uatf.Layouter;
import com.aripd.project.lgk.report.uatf.Writer;
import com.aripd.project.lgk.repository.UatfRepository;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.UatfService;
import javax.persistence.criteria.Join;
import org.joda.time.DateTime;

@Service("uatfService")
@Transactional(readOnly = true)
public class UatfServiceImpl implements UatfService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UatfRepository repository;
    @Autowired
    private ForwardingService forwardingService;

    public Uatf findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Uatf> findAll() {
        return repository.findAll();
    }

    public List<Uatf> findByForwardingId(Long forwarding_id) {
        return repository.findByForwardingId(forwarding_id);
    }

    public List<Uatf> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Uatf> cq = cb.createQuery(Uatf.class);
        Root<Uatf> root = cq.from(Uatf.class);
        Join<Uatf, Forwarding> forwarding = root.join(Uatf_.forwarding);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(forwarding.get(Forwarding_.startingTime), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Uatf> typedQuery = em.createQuery(cq);
        List<Uatf> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Uatf save(Uatf uatf) {
        return repository.save(uatf);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Uatf uatf) {
        repository.delete(uatf);
    }

    @Override
    public DatatablesResultSet<Uatf> getRecords(Long forwarding_id, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        String search = criteria.getSearch();
        List<DatatablesSortField> sortFields = criteria.getSortFields();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Uatf> cq = cb.createQuery(Uatf.class);
        Root<Uatf> root = cq.from(Uatf.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Predicate filter1 = cb.equal(root.get(Uatf_.forwarding), forwardingService.findOne(forwarding_id));
        predicateList.add(filter1);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Uatf_.code), "%" + search + "%");
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
        TypedQuery<Uatf> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Uatf> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Uatf>(resultList, totalRecords, displaySize);
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

        List<Uatf> uatfs = new ArrayList<Uatf>();

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String waybillNo = row.getCell(0).getStringCellValue();
            String code = row.getCell(1).getStringCellValue();
            String company = row.getCell(2).getStringCellValue();
            String county = row.getCell(3).getStringCellValue();
            String city = row.getCell(4).getStringCellValue();
            Integer loadWeightInTonne = (int) row.getCell(5).getNumericCellValue();

            Forwarding forwarding = forwardingService.findOneByWaybillNo(waybillNo);
            if (forwarding != null) {
                Uatf uatf = new Uatf();
                uatf.setForwarding(forwarding);
                uatf.setCode(code);
                uatf.setCompany(company);
                uatf.setCounty(county);
                uatf.setCity(city);
                uatf.setLoadWeightInTonne(loadWeightInTonne);

                uatfs.add(uatf);
            }
        }

        repository.save(uatfs);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Uatf Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

        // 6. Set the response properties
        String fileName = "UatfReport.xls";
        response.setHeader("Content-Disposition", "inline; filename="
                + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
