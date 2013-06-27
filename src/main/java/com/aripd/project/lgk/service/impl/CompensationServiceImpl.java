package com.aripd.project.lgk.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Compensation;
import com.aripd.project.lgk.domain.Compensation_;
import com.aripd.project.lgk.domain.Electricmeter;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.domain.Production_;
import com.aripd.project.lgk.report.compensation.FillManager;
import com.aripd.project.lgk.report.compensation.Layouter;
import com.aripd.project.lgk.report.compensation.Writer;
import com.aripd.project.lgk.repository.CompensationRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.CompensationService;
import com.aripd.project.lgk.service.ProductService;
import com.aripd.project.lgk.service.ShiftService;
import java.util.ArrayList;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;

@Service("compensationService")
@Transactional(readOnly = true)
public class CompensationServiceImpl implements CompensationService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CompensationRepository repository;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private ProductService productService;

    public Compensation findOne(Long id) {
        return repository.findOne(id);
    }

    public Compensation findOneByProductionAndElectricmeter(Production production, Electricmeter electricmeter) {
        return repository.findOneByProductionAndElectricmeter(production, electricmeter);
    }

    public List<Compensation> findAll() {
        return repository.findAll();
    }

    public List<Compensation> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Compensation> cq = cb.createQuery(Compensation.class);
        Root<Compensation> root = cq.from(Compensation.class);
        Join<Compensation, Production> production = root.join(Compensation_.production);

        // Filtering and Searching
        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(production.get(Production_.shiftdate), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);

        Long totalRecords = (long) em.createQuery(cq).getResultList().size();

        // Pagination
        TypedQuery<Compensation> typedQuery = em.createQuery(cq);
        List<Compensation> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Compensation save(Compensation compensation) {
        return repository.save(compensation);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Compensation compensation) {
        repository.delete(compensation);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Compensation Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, this.findByInterval(startingTime, endingTime));

        // 6. Set the response properties
        String fileName = "CompensationReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
