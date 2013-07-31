package com.aripd.project.lgk.model;

import com.aripd.member.domain.Member;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class ExpenseFilterByIntervalForm {

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date startingDate;
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date endingDate;
    private Member member;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
