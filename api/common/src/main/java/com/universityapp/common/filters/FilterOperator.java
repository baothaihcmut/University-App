package com.universityapp.common.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    LIKE("~");

    private String value;
}
