package com.aripd.project.lgk.model;

import java.util.Date;

public class EmployeeleaveReportModel {

    private Date date;
    private Integer qualified;
    private Integer used;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQualified() {
        return qualified;
    }

    public void setQualified(Integer qualified) {
        this.qualified = qualified;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
}
