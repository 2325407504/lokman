package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.member.domain.Member;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.model.ForwardingFilterByIntervalForm;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface ForwardingService {

    public Forwarding findOne(Long id);

    public Forwarding findOneByMemberAndId(Member member, Long id);

    public Forwarding findOneByWaybillNo(String waybillNo);

    public boolean isExistByWaybillNoExceptId(String waybillNo, Long id);

    public List<Forwarding> findByInterval(DateTime startingTime, DateTime endingTime);

    public Forwarding save(Forwarding formData);

    public void delete(Long id);

    public void delete(Forwarding forwarding);

    public DatatablesResultSet<Forwarding> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Forwarding> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void export(HttpServletResponse response, ForwardingFilterByIntervalForm formData);
}
