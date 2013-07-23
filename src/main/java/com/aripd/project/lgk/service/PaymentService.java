package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Payment;
import com.aripd.project.lgk.model.PaymentFilterByIntervalForm;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PaymentService {

    public Payment findOne(Long id);

    public Payment findOneByAccountAndId(Account account, Long id);

    public List<Payment> findByInterval(Date starting, Date ending, Long account_id);

    public Payment save(Payment payment);

    public void delete(Long id);

    public void delete(Payment payment);

    public DatatablesResultSet<Payment> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Payment> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void export(HttpServletResponse response, PaymentFilterByIntervalForm formData);
}
