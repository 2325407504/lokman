package com.aripd.project.lgk.domain;

import com.aripd.account.domain.Account;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    private boolean active;
    @Column(unique = true)
    private String taxNo;
    private String taxOffice;
    @NotEmpty
    private String name;
    private String address;
    private String phonenumber;
    private Account authorized;
    private boolean container;

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

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Account getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Account authorized) {
        this.authorized = authorized;
    }

    public boolean isContainer() {
        return container;
    }

    public void setContainer(boolean container) {
        this.container = container;
    }
}
