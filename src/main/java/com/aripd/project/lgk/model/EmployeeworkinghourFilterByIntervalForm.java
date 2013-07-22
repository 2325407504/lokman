package com.aripd.project.lgk.model;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Employeeworkinghourtype;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;

public class EmployeeworkinghourFilterByIntervalForm {

    @NotNull
    private DateTime startingTime;
    @NotNull
    private DateTime endingTime;
    private Employeeworkinghourtype employeeworkinghourtype;
    private Account account;

    public DateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(DateTime startingTime) {
        this.startingTime = startingTime;
    }

    public DateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(DateTime endingTime) {
        this.endingTime = endingTime;
    }

    public Employeeworkinghourtype getEmployeeworkinghourtype() {
        return employeeworkinghourtype;
    }

    public void setEmployeeworkinghourtype(Employeeworkinghourtype employeeworkinghourtype) {
        this.employeeworkinghourtype = employeeworkinghourtype;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
