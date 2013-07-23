package com.aripd.account.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Memberlog.class)
public class Memberlog_ extends BaseEntity_ {

    public static volatile SingularAttribute<Memberlog, Account> account;
    public static volatile SingularAttribute<Memberlog, String> sessionId;
    public static volatile SingularAttribute<Memberlog, String> ipAddress;
    public static volatile SingularAttribute<Memberlog, Long> executeTime;
    public static volatile SingularAttribute<Memberlog, String> url;
    public static volatile SingularAttribute<Memberlog, String> type;
}