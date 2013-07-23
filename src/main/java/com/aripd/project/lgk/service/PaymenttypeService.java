package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Paymenttype;

public interface PaymenttypeService {

    public Paymenttype findOne(Long id);

    public Paymenttype findOneByCode(String paymenttype_code);

    public List<Paymenttype> findAll();

    public Paymenttype save(Paymenttype paymenttype);

    public void delete(Long id);

    public void delete(Paymenttype paymenttype);

    public DatatablesResultSet<Paymenttype> getRecords(DatatablesCriteria criteria);
}
