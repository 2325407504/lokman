package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.time.DateTime;

import com.aripd.member.domain.Member;
import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Waybill.class)
public class Waybill_ extends BaseEntity_ {

    public static volatile SingularAttribute<Expense, Boolean> submitted;
    public static volatile SingularAttribute<Waybill, Member> member;
    public static volatile SingularAttribute<Waybill, Invoice> invoice;
    public static volatile SingularAttribute<Waybill, DateTime> documentDate;
    public static volatile SingularAttribute<Waybill, String> documentNo;
    public static volatile SingularAttribute<Waybill, String> company;
    public static volatile SingularAttribute<Waybill, String> driver;
    public static volatile SingularAttribute<Waybill, String> plate;
    public static volatile SetAttribute<Waybill, Outgoing> outgoings;
}
