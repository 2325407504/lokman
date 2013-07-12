package com.aripd.project.lgk.service.impl;

import java.io.IOException;
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
import com.aripd.project.lgk.domain.Endingpoint;
import com.aripd.project.lgk.domain.Endingpoint_;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Forwarding_;
import com.aripd.project.lgk.domain.Startingpoint;
import com.aripd.project.lgk.domain.Startingpoint_;
import com.aripd.project.lgk.report.forwarding.FillManager;
import com.aripd.project.lgk.report.forwarding.Layouter;
import com.aripd.project.lgk.report.forwarding.Writer;
import com.aripd.project.lgk.repository.ForwardingRepository;
import com.aripd.project.lgk.service.EndingpointService;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.QuotaService;
import com.aripd.project.lgk.service.StartingpointService;
import com.aripd.project.lgk.service.SubcontractorService;
import javax.persistence.criteria.Join;
import org.springframework.web.multipart.MultipartFile;

@Service("forwardingService")
@Transactional(readOnly = true)
public class ForwardingServiceImpl implements ForwardingService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ForwardingRepository repository;
    @Resource(name = "accountService")
    private AccountService accountService;
    @Resource(name = "subcontractorService")
    private SubcontractorService subcontractorService;
    @Resource(name = "quotaService")
    private QuotaService quotaService;
    @Resource(name = "startingpointService")
    private StartingpointService startingpointService;
    @Resource(name = "endingpointService")
    private EndingpointService endingpointService;

    public Forwarding findOne(Long id) {
        return repository.findOne(id);
    }

    public Forwarding findOneByWaybillNo(String waybillNo) {
        return repository.findOneByWaybillNo(waybillNo);
    }

    public Forwarding findOneByAccountAndId(Account account, Long id) {
        return repository.findOneByAccountAndId(account, id);
    }

    public List<Forwarding> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Forwarding> cq = cb.createQuery(Forwarding.class);
        Root<Forwarding> root = cq.from(Forwarding.class);

        Predicate predicate = cb.between(root.get(Forwarding_.startingTime), startingTime, endingTime);
        cq.where(predicate);

        TypedQuery<Forwarding> typedQuery = em.createQuery(cq);
        List<Forwarding> resultList = typedQuery.getResultList();

        return resultList;
    }

    public List<Forwarding> findByInterval(DateTime startingTime, DateTime endingTime, String plate, Long startingpoint_id, Long endingpoint_id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Forwarding> cq = cb.createQuery(Forwarding.class);
        Root<Forwarding> root = cq.from(Forwarding.class);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(root.get(Forwarding_.startingTime), startingTime, endingTime);
        Predicate predicate2 = null, predicate3 = null, predicate4 = null;
        if (plate.equalsIgnoreCase("") == false) {
            predicate2 = cb.equal(root.get(Forwarding_.plate), plate);
        }
        if (startingpoint_id != null) {
            Join<Forwarding, Startingpoint> startingpoint = root.join(Forwarding_.startingpoint);
            predicate3 = cb.equal(startingpoint.get(Startingpoint_.id), startingpoint_id);
        }
        if (endingpoint_id != null) {
            Join<Forwarding, Endingpoint> endingpoint = root.join(Forwarding_.endingpoint);
            predicate4 = cb.equal(endingpoint.get(Endingpoint_.id), endingpoint_id);
        }

        Predicate predicate = predicate1;
        if (predicate2 != null && predicate3 == null && predicate4 == null) {
            predicate = cb.and(predicate1, predicate2);
        } else if (predicate2 == null && predicate3 != null && predicate4 == null) {
            predicate = cb.and(predicate1, predicate3);
        } else if (predicate2 == null && predicate3 == null && predicate4 != null) {
            predicate = cb.and(predicate1, predicate4);
        } else if (predicate2 != null && predicate3 != null && predicate4 == null) {
            predicate = cb.and(predicate1, predicate2, predicate3);
        } else if (predicate2 != null && predicate3 == null && predicate4 != null) {
            predicate = cb.and(predicate1, predicate2, predicate4);
        } else if (predicate2 == null && predicate3 != null && predicate4 != null) {
            predicate = cb.and(predicate1, predicate3, predicate4);
        } else if (predicate2 != null && predicate3 != null && predicate4 != null) {
            predicate = cb.and(predicate1, predicate2, predicate3, predicate4);
        }

        predicateList.add(predicate);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        TypedQuery<Forwarding> typedQuery = em.createQuery(cq);
        List<Forwarding> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Forwarding save(Forwarding forwarding) {
        return repository.save(forwarding);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Forwarding forwarding) {
        repository.delete(forwarding);
    }

    public DatatablesResultSet<Forwarding> getRecords(DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Forwarding> cq = cb.createQuery(Forwarding.class);
        Root<Forwarding> root = cq.from(Forwarding.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate = cb.like(root.get(Forwarding_.waybillNo), "%" + search + "%");
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
        TypedQuery<Forwarding> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Forwarding> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Forwarding>(resultList, totalRecords, displaySize);
    }

    public DatatablesResultSet<Forwarding> getRecords(Principal principal, DatatablesCriteria criteria) {
        Integer displaySize = criteria.getDisplaySize();
        Integer displayStart = criteria.getDisplayStart();
        Integer pageNumber = criteria.getPageNumber();
        List<DatatablesSortField> sortFields = criteria.getSortFields();
        String search = criteria.getSearch();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Forwarding> cq = cb.createQuery(Forwarding.class);
        Root<Forwarding> root = cq.from(Forwarding.class);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();

        Account account = accountService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Forwarding_.account), account);

        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(root.get(Forwarding_.waybillNo), "%" + search + "%");
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
        TypedQuery<Forwarding> typedQuery = em.createQuery(cq);
        typedQuery = typedQuery.setFirstResult(displayStart);
        typedQuery = typedQuery.setMaxResults(displaySize);
        List<Forwarding> resultList = typedQuery.getResultList();

        return new DatatablesResultSet<Forwarding>(resultList, totalRecords, displaySize);
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

        List<Forwarding> forwardings = new ArrayList<Forwarding>();
        Forwarding forwarding;

        //while (rows.hasNext()) {
        for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
            //Row row = rows.next();
            Row row = worksheet.getRow(i);

            String username = row.getCell(0).getStringCellValue();
            String waybillNo = row.getCell(1).getStringCellValue();
            String driver = row.getCell(2).getStringCellValue();
            String plate = row.getCell(3).getStringCellValue();
            Date startingTime = row.getCell(4).getDateCellValue();
            Date endingTime = row.getCell(5).getDateCellValue();
            String startingpoint_code = row.getCell(6).getStringCellValue();
            String endingpoint_code = row.getCell(7).getStringCellValue();
            Integer startingKm = (int) row.getCell(8).getNumericCellValue();
            Integer endingKm = (int) row.getCell(9).getNumericCellValue();
            Integer loadWeightInTonne = (int) row.getCell(10).getNumericCellValue();
            String remark = row.getCell(11).getStringCellValue();
            BigDecimal shippingCost = new BigDecimal(row.getCell(12).getNumericCellValue());
            String subcontractor_code = row.getCell(13).getStringCellValue();
            String quota_code = row.getCell(14).getStringCellValue();

            forwarding = new Forwarding();
            forwarding.setSubmitted(true);
            forwarding.setAccount(accountService.findOneByUsername(username));
            forwarding.setWaybillNo(waybillNo);
            forwarding.setDriver(driver);
            forwarding.setPlate(plate);
            forwarding.setStartingTime(new DateTime(startingTime));
            forwarding.setEndingTime(new DateTime(endingTime));
            forwarding.setStartingpoint(startingpointService.findOneByCode(startingpoint_code));
            forwarding.setEndingpoint(endingpointService.findOneByCode(endingpoint_code));
            forwarding.setStartingKm(startingKm);
            forwarding.setEndingKm(endingKm);
            forwarding.setLoadWeightInTonne(loadWeightInTonne);
            forwarding.setRemark(remark);
            forwarding.setShippingCost(shippingCost);
            forwarding.setSubcontractor(subcontractorService.findOneByCode(subcontractor_code));
            forwarding.setQuota(quotaService.findOneByCode(quota_code));

            forwardings.add(forwarding);
        }

        repository.save(forwardings);
    }

    public void export(HttpServletResponse response, DateTime startingTime, DateTime endingTime, String plate, Long startingpoint_id, Long endingpoint_id) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Forwarding Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime, plate, startingpoint_id, endingpoint_id));

        // 6. Set the response properties
        String fileName = "ForwardingReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);

    }
}
