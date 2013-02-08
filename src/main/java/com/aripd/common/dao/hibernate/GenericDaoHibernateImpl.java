package com.aripd.common.dao.hibernate;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.aripd.common.dao.GenericDao;
import com.aripd.common.entity.GenericEntity;

/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD and
 * finder methods based on Hibernate and Spring AOP The finders are implemented
 * through the executeFinder method. Normally called by the
 * FinderIntroductionInterceptor
 */
public class GenericDaoHibernateImpl<T extends GenericEntity<T, PK>, PK extends Serializable>
		extends HibernateDaoSupport implements GenericDao<T, PK> {

	private Class<T> type;

	public GenericDaoHibernateImpl(Class<T> type) {
		this.type = type;
	}

	public PK persist(T o) {
		getSession().saveOrUpdate(o);
		return (PK) o.getId();
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T) getSession().get(type, id);
	}

	/**
	 * @deprecated
	 * @param o
	 */
	public void update(T o) {
		getSession().update(o);
	}

	public void remove(T o) {
		getSession().delete(o);
	}

}