package com.aripd.project.lgk.service;


import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.autocomplete.AutocompleteCriteria;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Waybill;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface WaybillService {

    public Waybill findOne(Long id);

    public Waybill findOneByAccountAndId(Account account, Long id);

    public Waybill findOneByDocumentNo(String documentNo);

    public List<Waybill> findByInterval(DateTime startingTime, DateTime endingTime);

    public Waybill save(Waybill formData);

    public void delete(Long id);

    public void delete(Waybill waybill);

    public DatatablesResultSet<Waybill> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Waybill> getRecords(Long invoice_id, DatatablesCriteria criteria);

    public List<String> getRecords(AutocompleteCriteria criteria);

    public void importData(MultipartFile file);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
