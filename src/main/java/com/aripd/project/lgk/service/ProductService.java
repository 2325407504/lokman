package com.aripd.project.lgk.service;

import java.util.List;

import com.aripd.common.dto.PagingCriteria;
import com.aripd.common.dto.ResultSet;
import com.aripd.project.lgk.domain.Product;

public interface ProductService {

    Product findOne(Long id);

    List<Product> findAll();

    Product save(Product product);

    void delete(Long id);

    void delete(Product product);

    ResultSet<Product> getRecords(PagingCriteria criteria);
}
