package com.aripd.project.lokman.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 * 
 * @author cem
 */
@StaticMetamodel(Uatf.class)
public class Uatf_ extends BaseEntity_ {

	public static volatile SingularAttribute<Uatf, Forwarding> forwarding;
	public static volatile SingularAttribute<Uatf, String> code;

}
