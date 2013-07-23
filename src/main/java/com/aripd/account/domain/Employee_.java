package com.aripd.account.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;
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
}
