package com.aripd.project.lgk.service;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Weighbridge;
import java.security.Principal;
import java.util.List;
import org.joda.time.DateTime;

public interface WeighbridgeService {

    public Weighbridge findOne(Long id);

    public List<Weighbridge> findByInterval(DateTime startingTime, DateTime endingTime);

    public Weighbridge save(Weighbridge weighbridge);

    public void delete(Long id);

    public void delete(Weighbridge weighbridge);

    public ResultSet<Weighbridge> getRecords(PagingCriteria criteria);

    public void importXLSX(String fileName, Principal principal);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
