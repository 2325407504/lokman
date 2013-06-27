package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Compensation.class)
public class Compensation_ extends BaseEntity_ {

    public static volatile SingularAttribute<Compensation, Production> production;
    public static volatile SingularAttribute<Compensation, Electricmeter> electricmeter;
    public static volatile SingularAttribute<Compensation, Integer> val;
}