package com.aripd.project.lgk.model;

import javax.validation.constraints.NotNull;

public class WaybillFilterByDocumentNoForm {

    @NotNull
    private String documentNo;

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
}
