package com.aripd.project.lgk.domain;

import com.aripd.account.domain.Account;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;
import javax.persistence.metamodel.SetAttribute;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Customer.class)
public class Customer_ extends BaseEntity_ {

    public static volatile SingularAttribute<Customer, Boolean> active;
    public static volatile SingularAttribute<Customer, String> taxNo;
    public static volatile SingularAttribute<Customer, String> taxOffice;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile SingularAttribute<Customer, String> address;
    public static volatile SingularAttribute<Customer, String> phonenumber;
    public static volatile SingularAttribute<Customer, Account> authorized;
    public static volatile SingularAttribute<Customer, Boolean> container;
    public static volatile SingularAttribute<Customer, Double> shippingcost;
    public static volatile SingularAttribute<Customer, Double> disposalcost;
    public static volatile SetAttribute<Customer, Shippingcost> shippingcosts;
    public static volatile SetAttribute<Customer, Disposalcost> disposalcosts;
}
