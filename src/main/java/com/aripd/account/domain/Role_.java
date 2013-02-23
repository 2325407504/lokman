package com.aripd.account.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 * 
 * @author cem
 */
@StaticMetamodel(Role.class)
public class Role_ extends BaseEntity_ {

	public static volatile SingularAttribute<Role, String> code;
	public static volatile SingularAttribute<Role, String> name;

}