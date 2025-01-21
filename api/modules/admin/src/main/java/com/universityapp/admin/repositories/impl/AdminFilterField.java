package com.universityapp.admin.repositories.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminFilterField {
    ID("id"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    EMAIL("email"),
    PHONE_NUMBER("phone_number");

    private final String field;
}
