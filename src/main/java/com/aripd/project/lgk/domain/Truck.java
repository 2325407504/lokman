package com.aripd.project.lgk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;

@Entity
@Table(name = "truck")
public class Truck extends BaseEntity {

    private static final long serialVersionUID = 6697864974198990570L;
    private boolean active;
    @ManyToOne
    private Region region;
    @Column(unique = true)
    private String plate;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
