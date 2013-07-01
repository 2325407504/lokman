package com.aripd.project.lgk.service.impl;

import com.aripd.project.lgk.domain.Machinetime_;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.project.lgk.domain.Machinetime;
import com.aripd.project.lgk.domain.Machine;
import com.aripd.project.lgk.domain.Production;
import com.aripd.project.lgk.domain.Production_;
import com.aripd.project.lgk.report.machinetime.FillManager;
import com.aripd.project.lgk.report.machinetime.Layouter;
import com.aripd.project.lgk.report.machinetime.Writer;
import com.aripd.project.lgk.repository.MachinetimeRepository;
import com.aripd.project.lgk.service.ProductionService;
import com.aripd.project.lgk.service.MachinetimeService;
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

@Service("machinetimeService")
@Transactional(readOnly = true)
public class MachinetimeServiceImpl implements MachinetimeService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MachinetimeRepository repository;
    @Autowired
    private ProductionService productionService;

    public Machinetime findOne(Long id) {
        return repository.findOne(id);
    }

    public Machinetime findOneByProductionAndMachine(Production production, Machine machine) {
        return repository.findOneByProductionAndMachine(production, machine);
    }

    public List<Machinetime> findAll() {
        return repository.findAll();
    }

    public List<Machinetime> findByProduction(Production production) {
        return repository.findByProduction(production);
    }


    public List<Machinetime> findByInterval(DateTime startingTime, DateTime endingTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Machinetime> cq = cb.createQuery(Machinetime.class);
        Root<Machinetime> root = cq.from(Machinetime.class);
        Join<Machinetime, Production> production = root.join(Machinetime_.production);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        Predicate predicate1 = cb.between(production.get(Production_.shiftdate), startingTime, endingTime);
        predicateList.add(predicate1);

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        cq.where(predicates);
        cq.orderBy(cb.asc(production.get(Production_.shiftdate)));

        TypedQuery<Machinetime> typedQuery = em.createQuery(cq);
        List<Machinetime> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Transactional
    public Machinetime save(Machinetime machinetime) {
        return repository.save(machinetime);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void delete(Machinetime machinetime) {
        repository.delete(machinetime);
    }

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime) {
        // 1. Create new workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. Create new worksheet
        HSSFSheet worksheet = workbook.createSheet("Machine Time Report");

        // 3. Define starting indices for rows and columns
        int startRowIndex = 0;
        int startColIndex = 0;

        // 4. Build layout
        List<Production> datasource = productionService.findByInterval(startingTime, endingTime);
        // Build title, date, and column headers
        Layouter.buildReport(worksheet, startRowIndex, startColIndex, datasource);

        // 5. Fill report
        FillManager.fillReport(worksheet, startRowIndex, startColIndex, datasource);

        // 6. Set the response properties
        String fileName = "MachinetimeReport.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // Make sure to set the correct content type
        response.setContentType("application/vnd.ms-excel");

        // 7. Write to the output stream
        Writer.write(response, worksheet);
    }
}
