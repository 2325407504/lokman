package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 * 
 * @author cem
 */
@StaticMetamodel(Driver.class)
public class Driver_ extends BaseEntity_ {

	public static volatile SingularAttribute<Driver, Boolean> active;
	public static volatile SingularAttribute<Driver, String> code;
	public static volatile SingularAttribute<Driver, String> name;
	public static volatile SingularAttribute<Driver, String> phonenumber;
	public static volatile SingularAttribute<Driver, Region> region;
	public static volatile ListAttribute<Driver, Trip> trips;

}
