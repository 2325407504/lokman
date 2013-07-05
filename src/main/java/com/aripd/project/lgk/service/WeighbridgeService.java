package com.aripd.project.lgk.service;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Weighbridge;
import java.security.Principal;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface WeighbridgeService {

    public Weighbridge findOne(Long id);

    public List<Weighbridge> findByInterval(DateTime startingTime, DateTime endingTime);

    public Weighbridge save(Weighbridge weighbridge);

    public void delete(Long id);

    public void delete(Weighbridge weighbridge);

    public DatatablesResultSet<Weighbridge> getRecords(DatatablesCriteria criteria);

    public void importXLS(MultipartFile file, Principal principal);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
