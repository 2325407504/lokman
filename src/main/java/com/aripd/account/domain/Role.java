package com.aripd.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;
    private String name;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Role() {
    }

    public Role(String code) {
        this.code = code;
    }

    public Role(String code, String name) {
        this.code = code;
        this.name = name;
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
