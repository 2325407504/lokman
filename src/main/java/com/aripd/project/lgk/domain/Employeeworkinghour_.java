package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity_;
import org.joda.time.DateTime;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Employeeworkinghour.class)
public class Employeeworkinghour_ extends BaseEntity_ {

    public static volatile SingularAttribute<Employeeworkinghour, Boolean> submitted;
    public static volatile SingularAttribute<Employeeworkinghour, Account> account;
    public static volatile SingularAttribute<Employeeworkinghour, Employeeworkinghourtype> employeeworkinghourtype;
    public static volatile SingularAttribute<Employeeworkinghour, DateTime> startingTime;
    public static volatile SingularAttribute<Employeeworkinghour, DateTime> endingTime;
    public static volatile SingularAttribute<Employeeworkinghour, String> remark;
}
