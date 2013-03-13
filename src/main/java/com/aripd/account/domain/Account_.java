package com.aripd.account.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 * 
 * @author cem
 */
@StaticMetamodel(Account.class)
public class Account_ extends BaseEntity_ {

	public static volatile SingularAttribute<Account, String> password;
	public static volatile SingularAttribute<Account, String> username;
	public static volatile SingularAttribute<Account, String> email;
	public static volatile SingularAttribute<Account, Boolean> active;
	public static volatile SingularAttribute<Account, Customer> customer;
	public static volatile ListAttribute<Account, Role> roles;

}