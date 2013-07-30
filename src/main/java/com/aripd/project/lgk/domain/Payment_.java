package com.aripd.project.lgk.domain;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.member.domain.Member;
import com.aripd.common.entity.BaseEntity_;
import java.util.Date;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Payment.class)
public class Payment_ extends BaseEntity_ {

    public static volatile SingularAttribute<Payment, Member> member;
    public static volatile SingularAttribute<Payment, Employee> employee;
    public static volatile SingularAttribute<Payment, Paymenttype> paymenttype;
    public static volatile SingularAttribute<Payment, Date> documentDate;
    public static volatile SingularAttribute<Payment, String> remark;
    public static volatile SingularAttribute<Payment, BigDecimal> amount;
}
