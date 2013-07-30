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

import com.aripd.member.domain.Member;
import com.aripd.common.entity.BaseEntity;
import com.aripd.common.util.ARIPDJodaDateTimeSerializer;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "production")
public class Production extends BaseEntity {

    @Column(nullable = false)
    private boolean submitted = false;
    @ManyToOne
    @JoinColumn(nullable = false, insertable = true, updatable = true)
    private Member member;
    @NotNull
    @Past
    @JsonSerialize(using = ARIPDJodaDateTimeSerializer.class)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(columnDefinition = "TIMESTAMP")
    private DateTime shiftdate;
    @Column(nullable = false)
    @NotNull
    private Double feed;
    @Column(columnDefinition = "TEXT", nullable = true)
    private String remark;
    @JsonIgnore
    @OneToMany(mappedBy = "production", cascade = CascadeType.REMOVE)
    private Set<Bigbag> bigbags;
    @JsonIgnore
    @OneToMany(mappedBy = "production", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Compensation> compensations;
    @JsonIgnore
    @OneToMany(mappedBy = "production", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Machinetime> machinetimes;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public DateTime getShiftdate() {
        return shiftdate;
    }

    public void setShiftdate(DateTime shiftdate) {
        this.shiftdate = shiftdate;
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

    public List<Compensation> getCompensations() {
        return compensations;
    }

    public void setCompensations(List<Compensation> compensations) {
        this.compensations = compensations;
    }

    public List<Machinetime> getMachinetimes() {
        return machinetimes;
    }

    public void setMachinetimes(List<Machinetime> machinetimes) {
        this.machinetimes = machinetimes;
    }
}
