package com.universityapp.faculities.repositories.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FaculityFilterField {
    NAME("name");

    private String value;
}
