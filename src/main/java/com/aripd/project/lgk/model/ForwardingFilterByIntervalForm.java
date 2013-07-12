package com.aripd.project.lgk.model;

import com.aripd.project.lgk.domain.Endingpoint;
import com.aripd.project.lgk.domain.Startingpoint;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;

public class ForwardingFilterByIntervalForm {

    @NotNull
    private DateTime startingTime;
    @NotNull
    private DateTime endingTime;
    private String plate;
    private Startingpoint startingpoint;
    private Endingpoint endingpoint;

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

    public Startingpoint getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(Startingpoint startingpoint) {
        this.startingpoint = startingpoint;
    }

    public Endingpoint getEndingpoint() {
        return endingpoint;
    }

    public void setEndingpoint(Endingpoint endingpoint) {
        this.endingpoint = endingpoint;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
