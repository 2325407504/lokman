package com.aripd.account.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.aripd.common.entity.BaseEntity;
import com.aripd.project.lgk.domain.Region;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @JsonIgnore
    @Column(nullable = false, unique = false)
    private String password;
    @Column(unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @JsonIgnore
    @Column(nullable = true, unique = false)
    private boolean active = false;
    @JsonIgnore
    @JoinTable(name = "account_role", joinColumns =
            @JoinColumn(name = "account_id"), inverseJoinColumns =
            @JoinColumn(name = "role_id"))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne
    private Region region;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    public Account() {
        roles = new ArrayList<Role>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
