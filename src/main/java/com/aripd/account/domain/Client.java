package com.aripd.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aripd.common.entity.BaseEntity;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "client")
public class Client extends BaseEntity {

    @NotNull
    @Column(nullable = true, unique = false)
    private String firstName;
    @NotNull
    @Column(nullable = true, unique = false)
    private String lastName;
    @Column(nullable = true, unique = false)
    private String phonenumber;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Client() {
    }

    public Client(String firstName, String lastName) {
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
