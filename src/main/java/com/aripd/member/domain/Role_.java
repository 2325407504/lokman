package com.aripd.member.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.common.entity.BaseEntity_;
import javax.persistence.metamodel.SetAttribute;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Role.class)
public class Role_ extends BaseEntity_ {

    public static volatile SingularAttribute<Role, String> code;
    public static volatile SingularAttribute<Role, String> name;
    public static volatile SetAttribute<Role, Member> members;
}