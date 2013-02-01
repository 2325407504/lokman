package com.aripd.common.constraint;

import java.io.Serializable;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class UniqueIDValidator implements ConstraintValidator<Unique, Serializable> {

	HibernateTemplate hibernateTemplate;

	private Class<?> entityClass;
	private String uniqueField;

	public void initialize(Unique unique) {
		entityClass = unique.entity();
		uniqueField = unique.property();
	}

	public boolean isValid(Serializable property, ConstraintValidatorContext cvContext) {

		String query = String.format("from %s where %s = ? ", entityClass.getName(), uniqueField);
		List<?> list = hibernateTemplate.find(query, property);

		return list != null && list.size() > 0;
	}

	public SessionFactory getSessionFactory() {
		return hibernateTemplate != null ? hibernateTemplate.getSessionFactory() : null;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
}