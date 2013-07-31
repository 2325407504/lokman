package com.aripd.project.lgk.domain;

import com.aripd.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aripd.common.entity.BaseEntity;
import com.aripd.common.util.ARIPDDateSerializer;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Length(min = 11, max = 11)
    @NotEmpty
    @Column(nullable = true, unique = true)
    private String tckimlikno;
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
    @JsonSerialize(using = ARIPDDateSerializer.class)
    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date birthdate;
    @JsonSerialize(using = ARIPDDateSerializer.class)
    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date employmentDate;
    @OneToOne
    private Member member;
    @ManyToOne
    private Region region;
    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Payment> payments;

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

    public String getTckimlikno() {
        return tckimlikno;
    }

    public void setTckimlikno(String tckimlikno) {
        this.tckimlikno = tckimlikno;
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

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
