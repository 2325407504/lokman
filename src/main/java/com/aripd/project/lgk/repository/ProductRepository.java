package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
