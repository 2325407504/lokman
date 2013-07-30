package com.aripd.project.lgk.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import com.aripd.member.domain.Member;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Expense;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ExpenseService {

    public Expense findOne(Long id);

    public Expense findOneByMemberAndId(Member member, Long id);

    public List<Expense> findByInterval(Date starting, Date ending, Long member_id);

    public Expense save(Expense expense);

    public void delete(Long id);

    public void delete(Expense expense);

    public DatatablesResultSet<Expense> getRecords(DatatablesCriteria criteria);

    public DatatablesResultSet<Expense> getRecords(Principal principal, DatatablesCriteria criteria);

    public void importData(MultipartFile file);

    public void export(HttpServletResponse response, Date starting, Date ending, Long member_id);
}
