package com.aripd.project.lgk.model;

import com.aripd.project.lgk.domain.Employee;
import com.aripd.project.lgk.domain.Employeeworkinghourtype;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;

public class EmployeeworkinghourFilterByIntervalForm {

    @NotNull
    private DateTime startingTime;
    @NotNull
    private DateTime endingTime;
    private Employeeworkinghourtype employeeworkinghourtype;
    private Employee employee;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
