package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.project.lgk.domain.Product;

public interface ProductService {

    Product findOne(Long id);

    Product findOneByCode(String code);

    List<Product> findAll();

    Product save(Product product);

    void delete(Long id);

    void delete(Product product);

    DatatablesResultSet<Product> getRecords(DatatablesCriteria criteria);
}
