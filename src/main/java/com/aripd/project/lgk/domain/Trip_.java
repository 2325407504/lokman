package com.aripd.project.lgk.domain;

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
@StaticMetamodel(Trip.class)
public class Trip_ extends BaseEntity_ {
	
	public static volatile SingularAttribute<Trip, Boolean> submitted;
	public static volatile SingularAttribute<Trip, Account> account;
	public static volatile SingularAttribute<Trip, Truck> truck;
	public static volatile SingularAttribute<Trip, Driver> driver;
	public static volatile SingularAttribute<Trip, String> startingPoint;
	public static volatile SingularAttribute<Trip, Integer> startingKm;
	public static volatile SingularAttribute<Trip, DateTime> startingTime;
	public static volatile SingularAttribute<Trip, String> endingPoint;
	public static volatile SingularAttribute<Trip, Integer> endingKm;
	public static volatile SingularAttribute<Trip, DateTime> endingTime;
	public static volatile SingularAttribute<Trip, Integer> loadWeightInTonne;
	public static volatile SingularAttribute<Trip, String> remark;
	
}