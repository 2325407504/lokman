package com.aripd.common.dao;

import java.io.Serializable;

/**
 * The basic GenericDao interface with CRUD methods Finders are added with
 * interface inheritance and AOP introductions for concrete implementations
 * 
 * Extended interfaces may declare methods starting with find... list...
 * iterate... or scroll... They will execute a preconfigured query that is
 * looked up based on the rest of the method name
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * inserts or updates record in database and returns ID if successful, null
	 * otherwise
	 */
	PK persist(T entityInstance);

	/**
	 * Returns an entity object representing the row with the key = to the ID
	 * param
	 * 
	 * @param id
	 * @return
	 */
	T get(PK id);

	/**
	 * Deletes Object represented by the entityObject parameter
	 * 
	 * @param entityObject
	 */
	void remove(T entityObject);

}