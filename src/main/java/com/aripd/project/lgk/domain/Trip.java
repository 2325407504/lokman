package com.aripd.project.lgk.domain;

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
import com.aripd.common.util.ARIPDJodaDateTimeSerializer;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "trip")
public class Trip extends BaseEntity {

    @Column(nullable = false)
    private boolean submitted = false;
    @ManyToOne
    @JoinColumn(nullable = false, insertable = true, updatable = true)
    private Account account;
    @NotNull
    @ManyToOne
    private Truck truck;
    @NotNull
    @ManyToOne
    private Driver driver;
    @NotEmpty
    private String startingpoint;
    @NotNull
    private Integer startingKm;
    @NotNull
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime startingTime;
    @NotEmpty
    private String endingpoint;
    @NotNull
    private Integer endingKm;
    @NotNull
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime endingTime;
    @NotNull
    private Integer weight;
    @Column(columnDefinition = "TEXT", nullable = true)
    private String remark;

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

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint;
    }

    public Integer getStartingKm() {
        return startingKm;
    }

    public void setStartingKm(Integer startingKm) {
        this.startingKm = startingKm;
    }

    public DateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(DateTime startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingpoint() {
        return endingpoint;
    }

    public void setEndingpoint(String endingpoint) {
        this.endingpoint = endingpoint;
    }

    public Integer getEndingKm() {
        return endingKm;
    }

    public void setEndingKm(Integer endingKm) {
        this.endingKm = endingKm;
    }

    public DateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(DateTime endingTime) {
        this.endingTime = endingTime;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}
