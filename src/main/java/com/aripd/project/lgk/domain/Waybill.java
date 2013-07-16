package com.aripd.project.lgk.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "waybill")
public class Waybill extends BaseEntity {

    @Column(nullable = false)
    private boolean submitted = false;
    @ManyToOne
    @JoinColumn(nullable = false, insertable = true, updatable = true)
    private Account account;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date documentDate;
    @Column(unique = true)
    private String documentNo;
    private String company;
    private String driver;
    private String plate;
    @JsonIgnore
    @OneToMany(mappedBy = "waybill", cascade = CascadeType.REMOVE)
    private Set<Outgoing> outgoings;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Set<Outgoing> getOutgoings() {
        return outgoings;
    }

    public void setOutgoings(Set<Outgoing> outgoings) {
        this.outgoings = outgoings;
    }
}
