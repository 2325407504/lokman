package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Waste.class)
public class Waste_ extends BaseEntity_ {

    public static volatile SingularAttribute<Waste, String> code;
    public static volatile SingularAttribute<Waste, String> name;
    public static volatile SingularAttribute<Waste, Wastegroup> wastegroup;
}
