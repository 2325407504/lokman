package com.aripd.project.lgk.model;

import com.aripd.account.domain.Account;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class PaymentFilterByIntervalForm {

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date startingDate;
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date endingDate;
    private Account account;

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
