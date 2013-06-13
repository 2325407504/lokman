package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bigbag")
public class Bigbag extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;
    @ManyToOne
    private Product product;
    @Column(nullable = false)
    @NotNull
    private Double weight;

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
