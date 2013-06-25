package com.aripd.project.lgk.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByTaxNo(String taxNo);
}
