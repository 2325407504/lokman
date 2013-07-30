package com.aripd.member.service;

import java.util.List;

import com.aripd.member.domain.Member;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;

public interface MemberService {

    public List<Member> findAll();

    public Member findOne(Long id);

    public Member findOneByUsername(String username);

    public Member save(Member member);

    public void delete(Long id);

    public DatatablesResultSet<Member> getRecords(DatatablesCriteria criteria);
}
