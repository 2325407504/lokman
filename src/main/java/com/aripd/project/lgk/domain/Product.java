package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String code;
    @NotEmpty
    private String name;
    @ManyToOne
    private Productgroup productgroup;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Productgroup getProductgroup() {
        return productgroup;
    }

    public void setProductgroup(Productgroup productgroup) {
        this.productgroup = productgroup;
    }
}
