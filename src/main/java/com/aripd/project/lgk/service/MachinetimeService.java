package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.project.lgk.domain.Machinetime;
import com.aripd.project.lgk.domain.Machine;
import com.aripd.project.lgk.domain.Production;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

public interface MachinetimeService {

    public Machinetime findOne(Long id);

    public Machinetime findOneByProductionAndMachine(Production production, Machine machine);

    public List<Machinetime> findAll();

    public List<Machinetime> findByProduction(Production production);

    public List<Machinetime> findByInterval(DateTime startingTime, DateTime endingTime);

    public Machinetime save(Machinetime machinetime);

    public void delete(Long id);

    public void delete(Machinetime machinetime);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
