package com.aripd.common.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * A meta model class used to create type safe queries.
 * 
 * @author cem
 */
@StaticMetamodel(BaseEntity.class)
public class BaseEntity_ {

	public static volatile SingularAttribute<BaseEntity, Long> id;
	public static volatile SingularAttribute<BaseEntity, Date> createdAt;
	public static volatile SingularAttribute<BaseEntity, Date> updatedAt;

}