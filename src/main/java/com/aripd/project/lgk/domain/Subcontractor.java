package com.aripd.project.lgk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "subcontractor")
public class Subcontractor extends BaseEntity {

    private static final long serialVersionUID = -7548528652541560063L;
    private boolean active;
    @Column(unique = true)
    private String code;
    private String name;
    @ManyToOne
    private Region region;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
