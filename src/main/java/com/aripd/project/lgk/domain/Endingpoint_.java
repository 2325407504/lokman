package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Endingpoint.class)
public class Endingpoint_ extends BaseEntity_ {

    public static volatile SingularAttribute<Endingpoint, String> code;
    public static volatile SingularAttribute<Endingpoint, String> name;
}
