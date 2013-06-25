package com.aripd.account.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Client.class)
public class Client_ extends BaseEntity_ {

    public static volatile SingularAttribute<Client, String> firstName;
    public static volatile SingularAttribute<Client, String> lastName;
}
