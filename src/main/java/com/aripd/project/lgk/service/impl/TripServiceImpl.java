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
import com.aripd.project.lgk.domain.Trip;
import com.aripd.project.lgk.domain.Trip_;
import com.aripd.project.lgk.domain.Truck;
import com.aripd.project.lgk.domain.Truck_;
import com.aripd.project.lgk.report.trip.FillManager;
import com.aripd.project.lgk.report.trip.Layouter;
import com.aripd.project.lgk.report.trip.Writer;
import com.aripd.project.lgk.repository.TripRepository;
import com.aripd.project.lgk.service.DriverService;
import com.aripd.project.lgk.service.TripService;
import com.aripd.project.lgk.service.TruckService;
import org.springframework.web.multipart.MultipartFile;

@Service("tripService")
@Transactional(readOnly = true)
public class TripServiceImpl implements TripService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private TripRepository repository;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "truckService")
    private TruckService truckService;
    @Resource(name = "driverService")
    private DriverService driverService;

    public Trip findOne(Long id) {
        return repository.findOne(id);
    }

    public Trip findOneByAccountAndId(Account account, Long id) {
        return repository.findOneByAccountAndId(account, id);
    }

    public List<Trip> findAll() {
        return repository.findAll();
    }

    public List<Trip> findByIntervalAndTruck(DateTime startingTime, DateTime endingTime, Truck truck) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> root = cq.from(Trip.class);
        Join<Trip, Truck> join = root.join(Trip_.truck);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Trip_.startingTime), startingTime, endingTime);
        Predicate predicate2 = cb.equal(join.get(Truck_.plate), truck.getPlate());
        Predicate predicate = cb.and(predicate1, predicate2);
        predicateList.add(predicate);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);
        cq.orderBy(cb.asc(root.get(Trip_.startingTime)));

        TypedQuery<Trip> typedQuery = em.createQuery(cq);
        List<Trip> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Trip save(Trip trip) {
        return repository.save(trip);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Trip trip) {
        repository.delete(trip);
    }

    public DatatablesResultSet<Trip> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        String search = criteria.getSearch();
        List<DatatablesSortField> sortFields = criteria.getSortFields();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> root = cq.from(Trip.class);
        Join<Trip, Account> account = root.join(Trip_.account);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Trip_.remark), "%" + search + "%");
            Predicate predicate2 = cb.like(root.get(Trip_.startingPoint), "%" + search + "%");
            Predicate predicate3 = cb.like(root.get(Trip_.endingPoint), "%" + search + "%");
            Predicate predicate4 = cb.like(account.get(Account_.username), "%" + search + "%");
            Predicate predicate = cb.or(predicate1, predicate2, predicate3, predicate4);
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
        TypedQuery<Trip> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Trip> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Trip>(resultList, totalRecords, displaySize);
    }

    public DatatablesResultSet<Trip> getRecords(Principal principal, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        String search = criteria.getSearch();
        List<DatatablesSortField> sortFields = criteria.getSortFields();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> root = cq.from(Trip.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Trip_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            //Predicate predicate = cb.equal(trip.get(Trip_.remark), search);
            Predicate predicate1 = cb.like(root.get(Trip_.remark), "%" + search + "%");
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
        TypedQuery<Trip> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Trip> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Trip>(resultList, totalRecords, displaySize);
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

        List<Trip> trips = new ArrayList<Trip>();
        Trip trip;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String truckPlate = row.getCell(1).getStringCellValue();
            String driverCode = row.getCell(2).getStringCellValue();
            String startingPoint = row.getCell(3).getStringCellValue();
            Integer startingKm = (int) row.getCell(4).getNumericCellValue();
            Date startingTime = row.getCell(5).getDateCellValue();
            String endingPoint = row.getCell(6).getStringCellValue();
            Integer endingKm = (int) row.getCell(7).getNumericCellValue();
            Date endingTime = row.getCell(8).getDateCellValue();
            Integer loadWeightInTonne = (int) row.getCell(9).getNumericCellValue();
            String remark = row.getCell(10).getStringCellValue();

            trip = new Trip();
            trip.setSubmitted(true);
            trip.setAccount(accountService.findOneByUsername(username));
            trip.setTruck(truckService.findOneByPlate(truckPlate));
            trip.setDriver(driverService.findOneByCode(driverCode));
            trip.setStartingPoint(startingPoint);
            trip.setStartingKm(startingKm);
            trip.setStartingTime(new DateTime(startingTime));
            trip.setEndingPoint(endingPoint);
            trip.setEndingKm(endingKm);
            trip.setEndingTime(new DateTime(endingTime));
            trip.setLoadWeightInTonne(loadWeightInTonne);
            trip.setRemark(remark);

            trips.add(trip);
        }

        repository.save(trips);
    }

    public void exportByIntervalAndTruck(HttpServletResponse response, DateTime startingTime, DateTime endingTime, Truck truck) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Trip Report for " + truck.getPlate());

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByIntervalAndTruck(startingTime, endingTime, truck));

        // 6. Set the response properties
        String fileName = "TripReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
