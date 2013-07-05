package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Bigbag;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface BigbagService {

    public Bigbag findOne(Long id);

    public List<Bigbag> findAll();

    public List<Bigbag> findByInterval(DateTime startingTime, DateTime endingTime);

    public Bigbag save(Bigbag bigbag);

    public void delete(Long id);

    public void delete(Bigbag bigbag);

    public List<Bigbag> findByProductionId(Long id);

    public DatatablesResultSet<Bigbag> getRecords(Long production_id, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
