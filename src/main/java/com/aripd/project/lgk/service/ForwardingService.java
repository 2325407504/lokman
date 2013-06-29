package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.account.domain.Account;
import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Forwarding;
import java.util.List;
import org.joda.time.DateTime;

public interface ForwardingService {

    public Forwarding findOne(Long id);

    public Forwarding findOneByAccountAndId(Account account, Long id);

    public Forwarding findOneByWaybillNo(String waybillNo);

    public List<Forwarding> findByInterval(DateTime startingTime, DateTime endingTime);

    public Forwarding save(Forwarding formData);

    public void delete(Long id);

    public void delete(Forwarding forwarding);

    public ResultSet<Forwarding> getRecords(PagingCriteria criteria);

    public ResultSet<Forwarding> getRecords(Principal principal, PagingCriteria criteria);

    public void importXLSX(String fileName);

    public void exportByInterval(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
