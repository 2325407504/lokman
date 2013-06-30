package com.aripd.project.lgk.service.impl;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;
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
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.common.dto.SortField;
import com.aripd.project.lgk.domain.Weighbridge;
import com.aripd.project.lgk.domain.Weighbridge_;
import com.aripd.project.lgk.report.weighbridge.FillManager;
import com.aripd.project.lgk.report.weighbridge.Layouter;
import com.aripd.project.lgk.report.weighbridge.Writer;
import com.aripd.project.lgk.repository.WeighbridgeRepository;
import com.aripd.project.lgk.service.WeighbridgeService;
import java.security.Principal;
import javax.annotation.Resource;
import org.apache.poi.ss.usermodel.Cell;
import org.joda.time.format.DateTimeFormat;

@Service("weighbridgeService")
@Transactional(readOnly = true)
public class WeighbridgeServiceImpl implements WeighbridgeService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private WeighbridgeRepository repository;
    @Resource(name = "accountService")
    private AccountService accountService;

    public Weighbridge findOne(Long id) {
        return repository.findOne(id);
    }

    public List<Weighbridge> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Weighbridge> cq = cb.createQuery(Weighbridge.class);
        Root<Weighbridge> root = cq.from(Weighbridge.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Weighbridge_.checkin), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Weighbridge> typedQuery = em.createQuery(cq);
        List<Weighbridge> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    @Transactional
    public Weighbridge save(Weighbridge weighbridge) {
        return repository.save(weighbridge);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Weighbridge weighbridge) {
        repository.delete(weighbridge);
    }

    public ResultSet<Weighbridge> getRecords(PagingCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<SortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Weighbridge> cq = cb.createQuery(Weighbridge.class);
        Root<Weighbridge> root = cq.from(Weighbridge.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Weighbridge_.customer), "%" + search + "%");
            Predicate predicate2 = cb.like(root.get(Weighbridge_.driver), "%" + search + "%");
            Predicate predicate = cb.or(predicate1, predicate2);
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
        TypedQuery<Weighbridge> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Weighbridge> resultList = typedQuery.getResultList();

        return new ResultSet<Weighbridge>(resultList, totalRecords, displaySize);
    }

    public void importXLSX(String fileName, Principal principal) {
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

        List<Weighbridge> weighbridges = new ArrayList<Weighbridge>();
        Weighbridge weighbridge;

        Account account = accountService.findOneByUsername(principal.getName());

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            //String clerk = row.getCell(0).getStringCellValue();
            Cell cell_clerk = row.getCell(0, Row.RETURN_BLANK_AS_NULL);
            String clerk = (cell_clerk != null) ? cell_clerk.getStringCellValue() : "---";

            String plate = row.getCell(1).getStringCellValue();

            //String driver = row.getCell(2).getStringCellValue();
            Cell cell_driver = row.getCell(2, Row.RETURN_BLANK_AS_NULL);
            String driver = (cell_driver != null) ? cell_driver.getStringCellValue() : "---";

            //String locationFrom = row.getCell(3).getStringCellValue();
            //String locationTo = row.getCell(4).getStringCellValue();
            Cell cell_locationFrom = row.getCell(3, Row.RETURN_BLANK_AS_NULL);
            Cell cell_locationTo = row.getCell(4, Row.RETURN_BLANK_AS_NULL);
            String locationFrom = (cell_locationFrom != null) ? cell_locationFrom.getStringCellValue() : "---";
            String locationTo = (cell_locationTo != null) ? cell_locationTo.getStringCellValue() : "---";

            DateTime _checkin_date = DateTime.parse(row.getCell(5).getStringCellValue(), DateTimeFormat.forPattern("dd.MM.yyyy"));
            DateTime _checkin_time = new DateTime(row.getCell(6).getDateCellValue());
            DateTime _checkout_date = DateTime.parse(row.getCell(7).getStringCellValue(), DateTimeFormat.forPattern("dd.MM.yyyy"));
            DateTime _checkout_time = new DateTime(row.getCell(8).getDateCellValue());
            DateTime checkin = new DateTime(_checkin_date.getYear(), _checkin_date.getMonthOfYear(), _checkin_date.getDayOfYear(), _checkin_time.getHourOfDay(), _checkin_time.getMinuteOfHour(), _checkin_time.getSecondOfMinute());
            DateTime checkout = new DateTime(_checkout_date.getYear(), _checkout_date.getMonthOfYear(), _checkout_date.getDayOfYear(), _checkout_time.getHourOfDay(), _checkout_time.getMinuteOfHour(), _checkout_time.getSecondOfMinute());

            //String goodtype = row.getCell(9).getStringCellValue();
            //String customer = row.getCell(10).getStringCellValue();
            Cell cell_goodtype = row.getCell(9, Row.RETURN_BLANK_AS_NULL);
            Cell cell_customer = row.getCell(10, Row.RETURN_BLANK_AS_NULL);
            String goodtype = (cell_goodtype != null) ? cell_goodtype.getStringCellValue() : "---";
            String customer = (cell_customer != null) ? cell_customer.getStringCellValue() : "---";

            Integer firstWeighing = (int) row.getCell(11).getNumericCellValue();
            Integer lastWeighing = (int) row.getCell(12).getNumericCellValue();

            weighbridge = new Weighbridge();
            weighbridge.setSubmitted(true);
            weighbridge.setAccount(account);
            weighbridge.setClerk(clerk);
            weighbridge.setPlate(plate);
            weighbridge.setDriver(driver);
            weighbridge.setLocationFrom(locationFrom);
            weighbridge.setLocationTo(locationTo);
            weighbridge.setCheckin(checkin);
            weighbridge.setCheckout(checkout);
            weighbridge.setGoodtype(goodtype);
            weighbridge.setCustomer(customer);
            weighbridge.setFirstWeighing(firstWeighing);
            weighbridge.setLastWeighing(lastWeighing);

            weighbridges.add(weighbridge);
        }

        repository.save(weighbridges);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Weighbridges");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

        // 6. Set the response properties
        String fileName = "WeighbridgeReport.xls";
        response.setHeader("Content-Disposition", "inline; filename="
                + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);

    }

}
