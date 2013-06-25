package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Waybill;

public interface WaybillService {

    Waybill findOne(Long id);

    Waybill findOneByAccountAndId(Account account, Long id);

    Waybill findOneByDocumentNo(String documentNo);

    Waybill save(Waybill formData);

    void delete(Long id);

    void delete(Waybill waybill);

    ResultSet<Waybill> getRecords(PagingCriteria criteria);

    ResultSet<Waybill> getRecords(Principal principal, PagingCriteria criteria);

    void exportXLS(HttpServletResponse response);

    void importXLSX(String fileName);
}
