package com.aripd.common.entity;

import java.io.Serializable;

/**
 * The basic GenericEntity interface that requires all entities to have a key.
 */
public interface GenericEntity<T, PK extends Serializable> {

	/**
	 * returns the unique key in the database for a given entity
	 */
	PK getId();

}
