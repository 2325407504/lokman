package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Column;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(unique = true)
    private String code;
    @NotEmpty
    private String name;

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
}
