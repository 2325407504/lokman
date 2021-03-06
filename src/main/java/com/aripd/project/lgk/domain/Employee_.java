package com.aripd.project.lgk.domain;

import com.aripd.member.domain.Member;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;
import javax.persistence.metamodel.ListAttribute;
import org.joda.time.DateTime;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Employee.class)
public class Employee_ extends BaseEntity_ {

    public static volatile SingularAttribute<Employee, String> address;
    public static volatile SingularAttribute<Employee, DateTime> birthdate;
    public static volatile SingularAttribute<Employee, DateTime> employmentDate;
    public static volatile SingularAttribute<Employee, String> firstName;
    public static volatile SingularAttribute<Employee, String> lastName;
    public static volatile SingularAttribute<Employee, String> phonenumber;
    public static volatile SingularAttribute<Employee, String> tckimlikno;
    public static volatile SingularAttribute<Employee, Member> member;
    public static volatile SingularAttribute<Employee, Region> region;
    public static volatile ListAttribute<Employee, Payment> payments;
    public static volatile ListAttribute<Employee, Employeeleave> employeeleaves;
    public static volatile ListAttribute<Employee, Employeeworkinghour> employeeworkinghours;
}
