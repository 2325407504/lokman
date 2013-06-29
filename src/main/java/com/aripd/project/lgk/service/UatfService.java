package com.aripd.project.lgk.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Uatf;
import org.joda.time.DateTime;

public interface UatfService {

    public Uatf findOne(Long id);

    public List<Uatf> findAll();

    public List<Uatf> findByInterval(DateTime startingTime, DateTime endingTime);

    public Uatf save(Uatf uatf);

    public void delete(Long id);

    public void delete(Uatf uatf);

    public List<Uatf> findByForwardingId(Long forwarding_id);

    public ResultSet<Uatf> getRecords(Long forwarding_id, PagingCriteria criteria);

    public void exportXLS(HttpServletResponse response);

    public void importXLSX(String fileName);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
