package com.aripd.member.service;

import com.aripd.member.domain.Memberlog;
import com.aripd.member.model.MemberlogFilterByIntervalForm;
import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

public interface MemberlogService {

    public Memberlog findOne(Long id);

    public List<Memberlog> findAll();

    public List<Memberlog> findByInterval(DateTime startingTime, DateTime endingTime);

    public Memberlog save(Memberlog memberlog);

    public void delete(Long id);

    public void delete(Memberlog memberlog);

    public DatatablesResultSet<Memberlog> getRecords(DatatablesCriteria criteria);

    public void export(HttpServletResponse response, MemberlogFilterByIntervalForm formData);
}
