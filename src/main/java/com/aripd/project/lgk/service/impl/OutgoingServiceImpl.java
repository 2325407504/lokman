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
import com.aripd.project.lgk.domain.Waybill_;
import com.aripd.project.lgk.domain.Waybill;
import com.aripd.project.lgk.domain.Outgoing;
import com.aripd.project.lgk.domain.Outgoing_;
import com.aripd.project.lgk.report.outgoing.FillManager;
import com.aripd.project.lgk.report.outgoing.Layouter;
import com.aripd.project.lgk.report.outgoing.Writer;
import com.aripd.project.lgk.repository.OutgoingRepository;
import com.aripd.project.lgk.service.WaybillService;
import com.aripd.project.lgk.service.OutgoingService;
import com.aripd.project.lgk.service.ProductService;
import javax.persistence.criteria.Join;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

@Service("outgoingService")
@Transactional(readOnly = true)
public class OutgoingServiceImpl implements OutgoingService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private OutgoingRepository repository;
    @Autowired
    private WaybillService waybillService;
    @Autowired
    private ProductService productService;

    public Outgoing findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Outgoing> findAll() {
        return repository.findAll();
    }

    public List<Outgoing> findByWaybillId(Long waybill_id) {
        return repository.findByWaybillId(waybill_id);
    }

    public List<Outgoing> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Outgoing> cq = cb.createQuery(Outgoing.class);
        Root<Outgoing> root = cq.from(Outgoing.class);
        Join<Outgoing, Waybill> waybill = root.join(Outgoing_.waybill);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(waybill.get(Waybill_.documentDate), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Outgoing> typedQuery = em.createQuery(cq);
        List<Outgoing> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    @Transactional
    public Outgoing save(Outgoing outgoing) {
        return repository.save(outgoing);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Outgoing outgoing) {
        repository.delete(outgoing);
    }

    @Override
    public DatatablesResultSet<Outgoing> getRecords(Long waybill_id, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        String search = criteria.getSearch();
        List<DatatablesSortField> sortFields = criteria.getSortFields();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Outgoing> cq = cb.createQuery(Outgoing.class);
        Root<Outgoing> outgoing = cq.from(Outgoing.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Predicate filter1 = cb.equal(outgoing.get(Outgoing_.waybill), waybillService.findOne(waybill_id));
        predicateList.add(filter1);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(outgoing.get(Outgoing_.remark), "%" + search + "%");
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        /*
         for (DatatablesSortField sortField : sortFields) {
         String field = sortField.getField();
         String direction = sortField.getDirection().getDirection();
         if (direction.equalsIgnoreCase("asc")) {
         cq.orderBy(cb.asc(outgoing.get(field)));
         } else if (direction.equalsIgnoreCase("desc")) {
         cq.orderBy(cb.desc(outgoing.get(field)));
         }
         }
         */

        Long totalRecords = (long) em.createQuery(cq).getResultList().size();

        // Pagination
        TypedQuery<Outgoing> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Outgoing> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Outgoing>(resultList, totalRecords, displaySize);
    }

    public void importXLS(MultipartFile file) {
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

        List<Outgoing> outgoings = new ArrayList<Outgoing>();

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String documentNo = row.getCell(0).getStringCellValue();
            String product_code = row.getCell(5).getStringCellValue();
            Integer weight = (int) row.getCell(5).getNumericCellValue();
            String remark = row.getCell(4).getStringCellValue();

            Waybill waybill = waybillService.findOneByDocumentNo(documentNo);
            if (waybill != null) {
                Outgoing outgoing = new Outgoing();
                outgoing.setWaybill(waybill);
                outgoing.setProduct(productService.findOneByCode(product_code));
                outgoing.setWeight(weight);
                outgoing.setRemark(remark);

                outgoings.add(outgoing);
            }
        }

        repository.save(outgoings);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Outgoing Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

        // 6. Set the response properties
        String fileName = "OutgoingReport.xls";
        response.setHeader("Content-Disposition", "inline; filename="
                + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
