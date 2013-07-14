package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "uatf")
public class Uatf extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "forwarding_id")
    private Forwarding forwarding;
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String code;
    @NotEmpty
    @Column(nullable = false)
    private String company;
    @NotEmpty
    @Column(nullable = false)
    private String county;
    @NotEmpty
    @Column(nullable = false)
    private String city;
    @NotNull
    @Column(nullable = false)
    private Integer weight;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Forwarding getForwarding() {
        return forwarding;
    }

    public void setForwarding(Forwarding forwarding) {
        this.forwarding = forwarding;
    }
}
