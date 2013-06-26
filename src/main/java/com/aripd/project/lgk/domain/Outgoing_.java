package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Outgoing.class)
public class Outgoing_ extends BaseEntity_ {

    public static volatile SingularAttribute<Outgoing, Waybill> waybill;
    public static volatile SingularAttribute<Outgoing, Product> product;
    public static volatile SingularAttribute<Outgoing, Integer> weight;
    public static volatile SingularAttribute<Outgoing, String> remark;
}
