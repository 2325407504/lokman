package com.aripd.project.lgk.domain;


import com.aripd.member.domain.Member;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.time.DateTime;

import com.aripd.common.entity.BaseEntity_;
import javax.persistence.metamodel.ListAttribute;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Weighbridge.class)
public class Weighbridge_ extends BaseEntity_ {

    public static volatile SingularAttribute<Weighbridge, Boolean> submitted;
    public static volatile SingularAttribute<Weighbridge, Member> member;
    public static volatile SingularAttribute<Weighbridge, String> clerk;
    public static volatile SingularAttribute<Weighbridge, String> plate;
    public static volatile SingularAttribute<Weighbridge, String> driver;
    public static volatile SingularAttribute<Weighbridge, String> locationFrom;
    public static volatile SingularAttribute<Weighbridge, String> locationTo;
    public static volatile SingularAttribute<Weighbridge, DateTime> checkin;
    public static volatile SingularAttribute<Weighbridge, DateTime> checkout;
    public static volatile SingularAttribute<Weighbridge, String> goodtype;
    public static volatile SingularAttribute<Weighbridge, String> customer;
    public static volatile SingularAttribute<Weighbridge, Integer> firstWeighing;
    public static volatile SingularAttribute<Weighbridge, Integer> lastWeighing;
    public static volatile ListAttribute<Weighbridge, Extrication> extrications;
}
