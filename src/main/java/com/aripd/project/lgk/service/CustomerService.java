package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Customer;

public interface CustomerService {

    Customer findOne(Long id);

    Customer findOneByTaxNo(String taxNo);

    List<Customer> findAll();

    Customer save(Customer customer);

    void delete(Long id);

    void delete(Customer customer);

    DatatablesResultSet<Customer> getRecords(DatatablesCriteria criteria);
}
