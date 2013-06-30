package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.time.DateTime;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity_;
import javax.persistence.metamodel.ListAttribute;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Production.class)
public class Production_ extends BaseEntity_ {

    public static volatile SingularAttribute<Production, Boolean> submitted;
    public static volatile SingularAttribute<Production, Account> account;
    public static volatile SingularAttribute<Production, DateTime> shiftdate;
    public static volatile SingularAttribute<Production, Double> feed;
    public static volatile SingularAttribute<Production, String> remark;
    public static volatile SetAttribute<Production, Bigbag> bigbags;
    public static volatile ListAttribute<Production, Compensation> compensations;
    public static volatile ListAttribute<Production, Machinetime> machinetimes;
}
