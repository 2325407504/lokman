package com.aripd.project.lgk.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.aripd.account.domain.Account;
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
    public static volatile SingularAttribute<Employeeleave, Account> account;
    public static volatile SingularAttribute<Employeeleave, Employeeleavetype> leavetype;
    public static volatile SingularAttribute<Employeeleave, Date> starting;
    public static volatile SingularAttribute<Employeeleave, Date> ending;
    public static volatile SingularAttribute<Employeeleave, String> remark;
}
