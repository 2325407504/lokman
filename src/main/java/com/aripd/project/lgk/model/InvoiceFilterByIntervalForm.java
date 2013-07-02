package com.aripd.project.lgk.model;

import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;

public class InvoiceFilterByIntervalForm {

    @NotNull
    private DateTime startingTime;
    @NotNull
    private DateTime endingTime;

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
}
