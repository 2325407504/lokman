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
@StaticMetamodel(Waybill.class)
public class Waybill_ extends BaseEntity_ {

    public static volatile SingularAttribute<Waybill, Boolean> submitted;
    public static volatile SingularAttribute<Waybill, Account> account;
    public static volatile SingularAttribute<Waybill, DateTime> documentDate;
    public static volatile SingularAttribute<Waybill, String> documentNo;
    public static volatile SingularAttribute<Waybill, String> company;
    public static volatile SingularAttribute<Waybill, String> driver;
    public static volatile SingularAttribute<Waybill, String> plate;
    public static volatile SetAttribute<Waybill, Outgoing> outgoings;
    public static volatile SingularAttribute<Waybill, String> invoiceCompany;
    public static volatile SingularAttribute<Waybill, String> invoiceNo;
    public static volatile SingularAttribute<Waybill, DateTime> invoiceDate;
    public static volatile SingularAttribute<Waybill, BigDecimal> invoiceAmount;
}
