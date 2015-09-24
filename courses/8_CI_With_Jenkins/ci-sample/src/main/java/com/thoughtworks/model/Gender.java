package com.thoughtworks.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("male"), FEMALE("female");

    private final String val;

    Gender(String val) {
        this.val = val;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.val;
    }
}
