package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Bigbag.class)
public class Bigbag_ extends BaseEntity_ {

    public static volatile SingularAttribute<Bigbag, Production> production;
    public static volatile SingularAttribute<Bigbag, Product> product;
    public static volatile SingularAttribute<Bigbag, Integer> feed;
}
