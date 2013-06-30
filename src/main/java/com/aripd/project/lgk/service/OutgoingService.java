package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Outgoing;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

public interface OutgoingService {

    public Outgoing findOne(Long id);

    public List<Outgoing> findAll();

    public List<Outgoing> findByInterval(DateTime startingTime, DateTime endingTime);

    public Outgoing save(Outgoing outgoing);

    public void delete(Long id);

    public void delete(Outgoing outgoing);

    public List<Outgoing> findByWaybillId(Long waybill_id);

    public ResultSet<Outgoing> getRecords(Long waybill_id, PagingCriteria criteria);

    public void importXLSX(String fileName);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
