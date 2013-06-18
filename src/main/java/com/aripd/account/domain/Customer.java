package com.aripd.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aripd.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Column(nullable = true, unique = false)
    private String firstName;
    @Column(nullable = true, unique = false)
    private String lastName;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Transient
    public String getFullname() {
        StringBuilder sb = new StringBuilder();

        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);

        return sb.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
