package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.member.domain.Member;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Production;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public interface ProductionService {

    public Production findOne(Long id);

    public Production findOneByMemberAndId(Member member, Long id);

    public Production findOneByShiftdate(DateTime dateTime);

    public List<Production> findByInterval(DateTime startingTime, DateTime endingTime);

    public Production save(Production formData);

    public void delete(Long id);

    public void delete(Production production);

    public DatatablesResultSet<Production> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Production> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void export(HttpServletResponse response, DateTime startingTime, DateTime endingTime);
}
