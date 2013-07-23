package com.aripd.account.model;

import com.aripd.account.domain.Account;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;

public class MemberlogFilterByIntervalForm {

    @NotNull
    private DateTime startingTime;
    @NotNull
    private DateTime endingTime;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
