package com.aripd.project.lgk.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "waste")
public class Waste extends BaseEntity {

    @Column(unique = true)
    private String code;
    @NotEmpty
    private String name;
    @ManyToOne
    private Wastegroup wastegroup;

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

    public Wastegroup getWastegroup() {
        return wastegroup;
    }

    public void setWastegroup(Wastegroup wastegroup) {
        this.wastegroup = wastegroup;
    }
}
