package com.aripd.common.dto.autocomplete;

public final class AutocompleteCriteria {

    private final String term;

    public AutocompleteCriteria(String term) {
        this.term = term;    }

    public String getTerm() {
        return term;
    }

}