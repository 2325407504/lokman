package com.aripd.project.lgk.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
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

    public DatatablesResultSet<Uatf> getRecords(Long forwarding_id, DatatablesCriteria criteria);

    public void importXLSX(String fileName);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
