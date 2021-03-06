package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.time.DateTime;

import com.aripd.member.domain.Member;
import com.aripd.common.entity.BaseEntity_;
import java.math.BigDecimal;
import javax.persistence.metamodel.SetAttribute;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Invoice.class)
public class Invoice_ extends BaseEntity_ {

    public static volatile SingularAttribute<Expense, Boolean> submitted;
    public static volatile SingularAttribute<Invoice, Member> member;
    public static volatile SingularAttribute<Invoice, DateTime> documentDate;
    public static volatile SingularAttribute<Invoice, String> documentNo;
    public static volatile SingularAttribute<Invoice, Customer> customer;
    public static volatile SingularAttribute<Invoice, BigDecimal> amount;
    public static volatile SetAttribute<Invoice, Waybill> waybills;
}
