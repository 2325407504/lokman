package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.member.domain.Member;
import com.aripd.common.entity.BaseEntity_;
import java.util.Date;

/**
 * A meta model class used to create type safe queries.
 *
 * @author cem
 */
@StaticMetamodel(Employeeleave.class)
public class Employeeleave_ extends BaseEntity_ {

    public static volatile SingularAttribute<Employeeleave, Boolean> submitted;
    public static volatile SingularAttribute<Employeeleave, Member> member;
    public static volatile SingularAttribute<Employeeleave, Employee> employee;
    public static volatile SingularAttribute<Employeeleave, Employeeleavetype> employeeleavetype;
    public static volatile SingularAttribute<Employeeleave, Date> startingDate;
    public static volatile SingularAttribute<Employeeleave, Date> endingDate;
    public static volatile SingularAttribute<Employeeleave, String> remark;
}
