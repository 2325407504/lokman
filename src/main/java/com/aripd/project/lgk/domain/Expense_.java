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
@StaticMetamodel(Expense.class)
public class Expense_ extends BaseEntity_ {

    public static volatile SingularAttribute<Expense, Boolean> submitted;
    public static volatile SingularAttribute<Expense, Member> member;
    public static volatile SingularAttribute<Expense, Expensetype> expensetype;
    public static volatile SingularAttribute<Expense, Date> documentDate;
    public static volatile SingularAttribute<Expense, String> company;
    public static volatile SingularAttribute<Expense, String> description;
    public static volatile SingularAttribute<Expense, BigDecimal> amount;
}
