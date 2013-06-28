package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Machinetime.class)
public class Machinetime_ extends BaseEntity_ {

    public static volatile SingularAttribute<Machinetime, Production> production;
    public static volatile SingularAttribute<Machinetime, Machine> machine;
    public static volatile SingularAttribute<Machinetime, Integer> val;
}