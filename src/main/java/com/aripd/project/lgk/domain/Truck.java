package com.aripd.project.lgk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "truck")
public class Truck extends BaseEntity {

    private boolean active;
    @ManyToOne
    private Region region;
    @NotBlank
    @Column(unique = true)
    private String plate;
    @Min(value = 0)
    private Integer km;

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

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }
}
