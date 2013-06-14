package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "region")
public class Region extends BaseEntity {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
