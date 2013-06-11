package com.aripd.project.lgk.domain;

import java.math.BigDecimal;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.time.DateTime;

import com.aripd.account.domain.Account;
import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Forwarding.class)
public class Forwarding_ extends BaseEntity_ {

    public static volatile SingularAttribute<Forwarding, Boolean> submitted;
    public static volatile SingularAttribute<Forwarding, Account> account;
    public static volatile SingularAttribute<Forwarding, String> waybillNo;
    public static volatile SingularAttribute<Forwarding, String> driver;
    public static volatile SingularAttribute<Forwarding, String> plate;
    public static volatile SingularAttribute<Forwarding, DateTime> startingTime;
    public static volatile SingularAttribute<Forwarding, DateTime> endingTime;
    public static volatile SingularAttribute<Forwarding, String> endingPoint;
    public static volatile SingularAttribute<Forwarding, Integer> loadWeightInTonne;
    public static volatile SingularAttribute<Forwarding, BigDecimal> shippingCost;
    public static volatile SingularAttribute<Forwarding, Subcontractor> subcontractor;
    public static volatile SingularAttribute<Forwarding, Quota> quota;
    public static volatile SetAttribute<Forwarding, Uatf> uatfs;
}
