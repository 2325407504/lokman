package com.aripd.project.lgk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.project.lgk.domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findOneByDocumentNo(String documentNo);
}
