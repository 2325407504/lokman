package com.aripd.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @NotNull
    @Column(nullable = false, unique = true)
    private String code;
    @NotNull
    private String name;
    @ManyToMany(mappedBy = "roles")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Account> accounts = new HashSet<Account>(0);

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

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
