package com.aripd.project.lgk.domain;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;
import com.aripd.common.utils.ARIPDJodaDateTimeSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "weighbridge")
public class Weighbridge extends BaseEntity {

    @Column(nullable = false)
    private boolean submitted = false;
    @ManyToOne
    @JoinColumn(nullable = false, insertable = true, updatable = true)
    private Account account;
    private String plate;
    private String driver;
    private String locationFrom;
    private String locationTo;
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime checkin;
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime checkout;
    private String goodtype;
    private String customer;
    private Integer firstWeighing;
    private Integer lastWeighing;

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

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(String locationFrom) {
        this.locationFrom = locationFrom;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo;
    }

    public DateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(DateTime checkin) {
        this.checkin = checkin;
    }

    public DateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(DateTime checkout) {
        this.checkout = checkout;
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getFirstWeighing() {
        return firstWeighing;
    }

    public void setFirstWeighing(Integer firstWeighing) {
        this.firstWeighing = firstWeighing;
    }

    public Integer getLastWeighing() {
        return lastWeighing;
    }

    public void setLastWeighing(Integer lastWeighing) {
        this.lastWeighing = lastWeighing;
    }
}
