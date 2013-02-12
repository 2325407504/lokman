package com.aripd.project.lokman.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 * 
 * @author cem
 */
@StaticMetamodel(Trip.class)
public class Trip_ extends BaseEntity_ {
	public static volatile SingularAttribute<Trip, String> remark;
}