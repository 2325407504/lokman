package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Compensation;
import com.aripd.project.lgk.domain.Electricmeter;
import com.aripd.project.lgk.domain.Production;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

public interface CompensationService {

    public Compensation findOne(Long id);

    public Compensation findOneByProductionAndElectricmeter(Production production, Electricmeter electricmeter);

    public Compensation findPrev(Long id);

    public List<Compensation> findAll();

    public List<Compensation> findByInterval(DateTime startingTime, DateTime endingTime);

    public Compensation save(Compensation compensation);

    public void delete(Long id);

    public void delete(Compensation compensation);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
