package com.aripd.project.lgk.domain;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "extrication", uniqueConstraints =
        @UniqueConstraint(columnNames = {"weighbridge_id", "waste_id"}))
public class Extrication extends BaseEntity {

    @ManyToOne
    private Weighbridge weighbridge;
    @ManyToOne
    private Waste waste;
    private Integer val;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Weighbridge getWeighbridge() {
        return weighbridge;
    }

    public void setWeighbridge(Weighbridge weighbridge) {
        this.weighbridge = weighbridge;
    }

    public Waste getWaste() {
        return waste;
    }

    public void setWaste(Waste waste) {
        this.waste = waste;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
