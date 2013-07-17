package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Proc.class)
public class Proc_ extends BaseEntity_ {

    public static volatile SingularAttribute<Proc, Boolean> active;
    public static volatile SingularAttribute<Proc, String> name;
    public static volatile SingularAttribute<Proc, String> description;
    public static volatile SingularAttribute<Proc, String> content;
}
