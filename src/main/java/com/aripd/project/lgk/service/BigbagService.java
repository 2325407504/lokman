package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Bigbag;
import com.aripd.project.lgk.domain.Uatf;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

public interface BigbagService {

    public Bigbag findOne(Long id);

    public List<Bigbag> findAll();

    public List<Bigbag> findByInterval(DateTime startingTime, DateTime endingTime);

    public Bigbag save(Bigbag bigbag);

    public void delete(Long id);

    public void delete(Bigbag bigbag);

    public List<Bigbag> findByProductionId(Long id);

    public ResultSet<Bigbag> getRecords(Long production_id, PagingCriteria criteria);

    public void importXLSX(String fileName);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
