package com.aripd.project.lgk.domain;

import com.aripd.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "machinetime", uniqueConstraints =
        @UniqueConstraint(columnNames = {"production_id", "machine_id"}))
public class Machinetime extends BaseEntity {

    @ManyToOne
    private Production production;
    @ManyToOne
    private Machine machine;
    @Column(nullable = false)
    private Integer val;

    @PreUpdate
    @Override
    public void preUpdate() {
        val = (val == null) ? 0 : val;
    }

    @PrePersist
    @Override
    public void prePersist() {
        val = (val == null) ? 0 : val;
    }

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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
