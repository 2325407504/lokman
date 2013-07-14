package com.aripd.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aripd.common.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Column(nullable = true, unique = true)
    private String tckimlik;
    @NotEmpty
    @Column(nullable = true)
    private String firstName;
    @NotEmpty
    @Column(nullable = true)
    private String lastName;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String phonenumber;
    @Past
    @DateTimeFormat(style = "S-")
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date birthdate;
    @Past
    @DateTimeFormat(style = "S-")
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date startingDate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Transient
    public String getFullname() {
        StringBuilder sb = new StringBuilder();

        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);

        return sb.toString();
    }

    public String getTckimlik() {
        return tckimlik;
    }

    public void setTckimlik(String tckimlik) {
        this.tckimlik = tckimlik;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }
}
