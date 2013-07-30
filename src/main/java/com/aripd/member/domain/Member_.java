package com.aripd.member.domain;

import com.aripd.project.lgk.domain.Employee;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;
import com.aripd.project.lgk.domain.Customer;
import javax.persistence.metamodel.ListAttribute;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Member.class)
public class Member_ extends BaseEntity_ {

    public static volatile SingularAttribute<Member, String> password;
    public static volatile SingularAttribute<Member, String> username;
    public static volatile SingularAttribute<Member, String> email;
    public static volatile SingularAttribute<Member, Boolean> active;
    public static volatile ListAttribute<Member, Role> roles;
    public static volatile SingularAttribute<Member, Employee> employee;
    public static volatile SingularAttribute<Member, Customer> customer;
}