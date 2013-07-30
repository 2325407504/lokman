package com.aripd.member.model;

import com.aripd.member.domain.Member;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;

public class MemberlogFilterByIntervalForm {

    @NotNull
    private DateTime startingTime;
    @NotNull
    private DateTime endingTime;
    private Member member;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
