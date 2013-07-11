package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Startingpoint.class)
public class Startingpoint_ extends BaseEntity_ {

    public static volatile SingularAttribute<Startingpoint, String> code;
    public static volatile SingularAttribute<Startingpoint, String> name;
}
