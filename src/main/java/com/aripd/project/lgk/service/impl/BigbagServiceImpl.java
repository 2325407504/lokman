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
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.domain.Production_;
import com.aripd.project.lgk.domain.Product;
import com.aripd.project.lgk.domain.Bigbag;
import com.aripd.project.lgk.domain.Bigbag_;
import com.aripd.project.lgk.report.bigbag.FillManager;
import com.aripd.project.lgk.report.bigbag.Layouter;
import com.aripd.project.lgk.report.bigbag.Writer;
import com.aripd.project.lgk.repository.BigbagRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.BigbagService;
import com.aripd.project.lgk.service.ProductService;
import java.util.Date;
import javax.persistence.criteria.Join;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

@Service("bigbagService")
@Transactional(readOnly = true)
public class BigbagServiceImpl implements BigbagService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private BigbagRepository repository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ProductService productService;

    public Bigbag findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Bigbag> findAll() {
        return repository.findAll();
    }

    public List<Bigbag> findByProductionId(Long id) {
        return repository.findByProductionId(id);
    }

    public List<Bigbag> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bigbag> cq = cb.createQuery(Bigbag.class);
        Root<Bigbag> root = cq.from(Bigbag.class);
        Join<Bigbag, Production> production = root.join(Bigbag_.production);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(production.get(Production_.shiftdate), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Bigbag> typedQuery = em.createQuery(cq);
        List<Bigbag> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Bigbag save(Bigbag bigbag) {
        return repository.save(bigbag);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Bigbag bigbag) {
        repository.delete(bigbag);
    }

    @Override
    public DatatablesResultSet<Bigbag> getRecords(Long production_id, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        String search = criteria.getSearch();
        List<DatatablesSortField> sortFields = criteria.getSortFields();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bigbag> cq = cb.createQuery(Bigbag.class);
        Root<Bigbag> bigbag = cq.from(Bigbag.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Predicate filter1 = cb.equal(bigbag.get(Bigbag_.production), productionService.findOne(production_id));
        predicateList.add(filter1);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.equal(bigbag.get(Bigbag_.weight), search);
            predicateList.add(predicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        // Sorting
        /*for (DatatablesSortField sortField : sortFields) {
         String field = sortField.getField();
         String direction = sortField.getDirection().getDirection();
         if (direction.equalsIgnoreCase("asc")) {
         cq.orderBy(cb.asc(bigbag.get(field)));
         } else if (direction.equalsIgnoreCase("desc")) {
         cq.orderBy(cb.desc(bigbag.get(field)));
         }
         }*/

        Long totalRecords = (long) em.createQuery(cq).getResultList().size();

        // Pagination
        TypedQuery<Bigbag> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Bigbag> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Bigbag>(resultList, totalRecords, displaySize);
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

        List<Bigbag> bigbags = new ArrayList<Bigbag>();

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            Date shiftdate = row.getCell(0).getDateCellValue();
            Product product = productService.findOneByCode(row.getCell(2).getStringCellValue());
            Double weight = row.getCell(3).getNumericCellValue();

            Production production = productionService.findOneByShiftdate(new DateTime(shiftdate));
            if (production != null) {
                Bigbag bigbag = new Bigbag();
                bigbag.setProduction(production);
                bigbag.setProduct(product);
                bigbag.setWeight(weight);

                bigbags.add(bigbag);
            }
        }

        repository.save(bigbags);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Bigbag Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

        // 6. Set the response properties
        String fileName = "BigbagReport.xls";
        response.setHeader("Content-Disposition", "inline; filename="
                + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
