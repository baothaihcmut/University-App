package com.universityapp.common.enums;

public enum Role {
    STUDENT("STUDENT"),
    TEACHER("TEACHER"),
    ADMIN("ADMIN"),
    OTHER("OTHER"),
    ;

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}