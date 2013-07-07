package com.aripd.project.lgk.model;

import com.aripd.project.lgk.domain.Truck;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;

public class UserTripFilterByIntervalAndTruckForm {

    @NotNull
    private DateTime startingTime;
    @NotNull
    private DateTime endingTime;
    @NotNull
    private Truck truck;

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

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
