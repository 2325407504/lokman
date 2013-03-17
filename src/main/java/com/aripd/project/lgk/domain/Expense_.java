package com.aripd.project.lgk.domain;

import java.math.BigDecimal;

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
@StaticMetamodel(Expense.class)
public class Expense_ extends BaseEntity_ {

	public static volatile SingularAttribute<Expense, Boolean> submitted;
	public static volatile SingularAttribute<Expense, Account> account;
	public static volatile SingularAttribute<Expense, DateTime> documentDate;
	public static volatile SingularAttribute<Expense, String> company;
	public static volatile SingularAttribute<Expense, String> description;
	public static volatile SingularAttribute<Expense, BigDecimal> amount;

}
