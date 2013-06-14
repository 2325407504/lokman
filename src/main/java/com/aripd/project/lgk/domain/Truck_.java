package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Truck.class)
public class Truck_ extends BaseEntity_ {

    public static volatile SingularAttribute<Truck, Boolean> active;
    public static volatile SingularAttribute<Truck, Region> region;
    public static volatile SingularAttribute<Truck, String> plate;
    public static volatile SingularAttribute<Truck, Integer> km;
    //public static volatile ListAttribute<Truck, Trip> trips;
}
