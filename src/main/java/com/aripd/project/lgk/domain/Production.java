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
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity;
import com.aripd.common.utils.ARIPDJodaDateTimeSerializer;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "production")
public class Production extends BaseEntity {

    @Column(nullable = false)
    private boolean submitted = false;
    @ManyToOne
    @JoinColumn(nullable = false, insertable = true, updatable = true)
    private Account account;
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime shiftdate;
    @ManyToOne
    private Shift shift;
    @Column(nullable = false)
    @NotNull
    private Double feed;
    @Column(columnDefinition = "TEXT", nullable = true)
    private String remark;
    @JsonIgnore
    @OneToMany(mappedBy = "production", cascade = CascadeType.REMOVE)
    private Set<Bigbag> bigbags;

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

    public DateTime getShiftdate() {
        return shiftdate;
    }

    public void setShiftdate(DateTime shiftdate) {
        this.shiftdate = shiftdate;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Double getFeed() {
        return feed;
    }

    public void setFeed(Double feed) {
        this.feed = feed;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<Bigbag> getBigbags() {
        return bigbags;
    }

    public void setBigbags(Set<Bigbag> bigbags) {
        this.bigbags = bigbags;
    }
}
