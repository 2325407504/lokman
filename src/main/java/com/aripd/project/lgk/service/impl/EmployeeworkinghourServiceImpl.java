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

import com.aripd.member.domain.Member;
import com.aripd.member.domain.Member_;
import com.aripd.member.service.MemberService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesSortField;
import com.aripd.project.lgk.domain.Employee;
import com.aripd.project.lgk.domain.Employeeworkinghour;
import com.aripd.project.lgk.domain.Employeeworkinghour_;
import com.aripd.project.lgk.domain.Employeeworkinghourtype;
import com.aripd.project.lgk.model.EmployeeworkinghourFilterByIntervalForm;
import com.aripd.project.lgk.report.employeeworkinghour.FillManager;
import com.aripd.project.lgk.report.employeeworkinghour.Layouter;
import com.aripd.project.lgk.report.employeeworkinghour.Writer;
import com.aripd.project.lgk.repository.EmployeeworkinghourRepository;
import com.aripd.project.lgk.service.EmployeeService;
import com.aripd.project.lgk.service.EmployeeworkinghourService;
import com.aripd.project.lgk.service.EmployeeworkinghourtypeService;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

@Service("employeeworkinghourService")
@Transactional(readOnly = true)
public class EmployeeworkinghourServiceImpl implements EmployeeworkinghourService {
    
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EmployeeworkinghourRepository repository;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "employeeService")
    private EmployeeService employeeService;
    @Resource(name = "employeeworkinghourtypeService")
    private EmployeeworkinghourtypeService employeeworkinghourtypeService;
    
    public Employeeworkinghour findOne(Long id) {
        return repository.findOne(id);
    }
    
    public List<Employeeworkinghour> findByEmployee(Employee employee) {
        return repository.findByEmployee(employee);
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
        Join<Employeeworkinghour, Member> member = root.join(Employeeworkinghour_.member);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();
        
        if ((search != null) && (!(search.isEmpty()))) {
            Predicate predicate1 = cb.like(member.get(Member_.username), "%" + search + "%");
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
        
        Member member = memberService.findOneByUsername(principal.getName());
        Predicate predicate_ = cb.equal(root.get(Employeeworkinghour_.member), member);
        
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
    
    public void importData(MultipartFile file, Principal principal) {
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
            
            Member member = memberService.findOneByUsername(principal.getName());
            
            String tckimlikno = row.getCell(0).getStringCellValue();
            String workinghourtype_code = row.getCell(1).getStringCellValue();
            Date startingTime = row.getCell(2).getDateCellValue();
            Date endingTime = row.getCell(3).getDateCellValue();
            String remark = row.getCell(4).getStringCellValue();
            
            employeeworkinghour = new Employeeworkinghour();
            employeeworkinghour.setSubmitted(true);
            employeeworkinghour.setMember(member);
            employeeworkinghour.setEmployee(employeeService.findOneByTckimlikno(tckimlikno));
            employeeworkinghour.setEmployeeworkinghourtype(employeeworkinghourtypeService.findOneByCode(workinghourtype_code));
            employeeworkinghour.setStartingTime(new DateTime(startingTime));
            employeeworkinghour.setEndingTime(new DateTime(endingTime));
            employeeworkinghour.setRemark(remark);
            
            workinghours.add(employeeworkinghour);
        }
        
        repository.save(workinghours);
    }
    
    public void export(HttpServletResponse response, EmployeeworkinghourFilterByIntervalForm formData) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Employee Working Hours");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.retrieveDatasource(formData));

        // 6. Set the response properties
        String fileName = "EmployeeWorkingHourReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
    
    public List<Employeeworkinghour> retrieveDatasource(EmployeeworkinghourFilterByIntervalForm formData) {
        Employee employee = employeeService.findOne(formData.getEmployee().getId());
        DateTime startingTime = formData.getStartingTime();
        DateTime endingTime = formData.getEndingTime();
        Employeeworkinghourtype employeeworkinghourtype = formData.getEmployeeworkinghourtype();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employeeworkinghour> cq = cb.createQuery(Employeeworkinghour.class);
        Root<Employeeworkinghour> root = cq.from(Employeeworkinghour.class);
        
        Predicate predicate1 = cb.between(root.get(Employeeworkinghour_.startingTime), startingTime, endingTime);
        Predicate predicate2 = cb.equal(root.get(Employeeworkinghour_.employee), employee);
        Predicate predicate3 = cb.equal(root.get(Employeeworkinghour_.employeeworkinghourtype), employeeworkinghourtype);
        Predicate predicate = cb.and(predicate1, predicate2, predicate3);
        
        List<Predicate> predicateList = new ArrayList<Predicate>();
        predicateList.add(predicate);
        
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);
        
        TypedQuery<Employeeworkinghour> typedQuery = em.createQuery(cq);
        List<Employeeworkinghour> resultList = typedQuery.getResultList();
        
        return resultList;
    }
}
