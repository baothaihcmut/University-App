package com.universityapp.users.repositories.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserField {
    EMAIL("email"),
    PHONE_NUMBER("phone_number");

    private String field;
}
