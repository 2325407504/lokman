package com.aripd.project.lgk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "quota")
public class Quota extends BaseEntity {

    private boolean active;
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String code;
    @NotEmpty
    private String name;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

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
}
