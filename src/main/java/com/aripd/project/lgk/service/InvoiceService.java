package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Invoice;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface InvoiceService {

    public Invoice findOne(Long id);

    public Invoice findOneByDocumentNo(String documentNo);

    public List<Invoice> findByInterval(DateTime startingTime, DateTime endingTime);

    public Invoice save(Invoice formData);

    public void delete(Long id);

    public void delete(Invoice invoice);

    public DatatablesResultSet<Invoice> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Invoice> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
