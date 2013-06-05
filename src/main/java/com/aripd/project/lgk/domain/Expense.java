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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "expense")
public class Expense extends BaseEntity {

    private static final long serialVersionUID = 7142407700649461014L;
    @Column(nullable = false)
    private boolean submitted = false;
    @ManyToOne
    @JoinColumn(nullable = false, insertable = true, updatable = true)
    private Account account;
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime documentDate;
    private String company;
    private String description;
    @Column(nullable = false)
    @NotNull
    private BigDecimal amount;

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

    public DateTime getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(DateTime documentDate) {
        this.documentDate = documentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
