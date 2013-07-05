package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Expensetype.class)
public class Expensetype_ extends BaseEntity_ {

    public static volatile SingularAttribute<Expensetype, String> code;
    public static volatile SingularAttribute<Expensetype, String> name;
}
