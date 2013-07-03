package com.aripd.account.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;
import com.aripd.project.lgk.domain.Region;
import javax.persistence.metamodel.SetAttribute;

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
    public static volatile SingularAttribute<Account, Client> client;
    public static volatile SingularAttribute<Account, Region> region;
    public static volatile SetAttribute<Account, Role> roles;
}