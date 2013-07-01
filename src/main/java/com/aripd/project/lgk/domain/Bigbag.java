package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "bigbag")
public class Bigbag extends BaseEntity {

    @ManyToOne
    private Production production;
    @ManyToOne
    private Product product;
    @Column(nullable = false)
    @NotNull
    private Double weight;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
