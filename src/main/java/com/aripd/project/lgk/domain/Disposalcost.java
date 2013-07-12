package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "disposalcost")
public class Disposalcost extends BaseEntity {

    @ManyToOne
    private Customer customer;
    @Column(nullable = false)
    @NotNull
    private Double cost;

    public Disposalcost() {
    }

    public Disposalcost(Customer customer, Double cost) {
        this.customer = customer;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
