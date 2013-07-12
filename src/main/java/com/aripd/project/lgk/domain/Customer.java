package com.aripd.project.lgk.domain;

import com.aripd.account.domain.Account;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aripd.common.entity.BaseEntity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    private boolean active;
    private boolean container;
    @NotBlank
    @Column(unique = true)
    private String taxNo;
    @NotBlank
    private String taxOffice;
    @NotEmpty
    private String name;
    private String address;
    private String phonenumber;
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private Account authorized;
    private Double shippingcost;
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Set<Shippingcost> shippingcosts;
    private Double disposalcost;
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Set<Disposalcost> disposalcosts;

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

    public Set<Shippingcost> getShippingcosts() {
        return shippingcosts;
    }

    public void setShippingcosts(Set<Shippingcost> shippingcosts) {
        this.shippingcosts = shippingcosts;
    }

    public Set<Disposalcost> getDisposalcosts() {
        return disposalcosts;
    }

    public void setDisposalcosts(Set<Disposalcost> disposalcosts) {
        this.disposalcosts = disposalcosts;
    }

    public Double getShippingcost() {
        return shippingcost;
    }

    public void setShippingcost(Double shippingcost) {
        this.shippingcost = shippingcost;
    }

    public Double getDisposalcost() {
        return disposalcost;
    }

    public void setDisposalcost(Double disposalcost) {
        this.disposalcost = disposalcost;
    }
}
