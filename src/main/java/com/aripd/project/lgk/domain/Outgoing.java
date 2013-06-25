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

@Entity
@Table(name = "outgoing")
public class Outgoing extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "waybill_id")
    private Waybill waybill;
    @ManyToOne
    private Product product;
    @Column(nullable = false)
    @NotNull
    private Integer weight;
    private String remark;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
