package com.aripd.project.lgk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;
import com.aripd.common.util.ARIPDJodaDateTimeSerializer;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Hours;

@Entity
@Table(name = "employeeworkinghour")
public class Employeeworkinghour extends BaseEntity {

    @Column(nullable = false)
    private boolean submitted = false;
    @ManyToOne
    @JoinColumn(nullable = false, insertable = true, updatable = true)
    private Account account;
    @ManyToOne
    private Employeeworkinghourtype employeeworkinghourtype;
    @NotNull
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime startingDateTime;
    @NotNull
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime endingDateTime;
    private String remark;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Transient
    public long getNofWorkhours() {
        return Hours.hoursBetween(startingDateTime, endingDateTime).getHours();
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

    public Employeeworkinghourtype getEmployeeworkinghourtype() {
        return employeeworkinghourtype;
    }

    public void setEmployeeworkinghourtype(Employeeworkinghourtype employeeworkinghourtype) {
        this.employeeworkinghourtype = employeeworkinghourtype;
    }

    public DateTime getStartingDateTime() {
        return startingDateTime;
    }

    public void setStartingDateTime(DateTime startingDateTime) {
        this.startingDateTime = startingDateTime;
    }

    public DateTime getEndingDateTime() {
        return endingDateTime;
    }

    public void setEndingDateTime(DateTime endingDateTime) {
        this.endingDateTime = endingDateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
