package com.aripd.project.lgk.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;
import com.aripd.common.utils.ARIPDJodaDateTimeSerializer;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false, insertable = true, updatable = true)
    private Account account;
    private String documentNo;
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime documentDate;
    @ManyToOne
    private Customer customer;
    @Column(nullable = false)
    @NotNull
    private BigDecimal amount;
    @OneToOne(mappedBy = "invoice")
    private Waybill waybill;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public DateTime getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(DateTime documentDate) {
        this.documentDate = documentDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }
}
