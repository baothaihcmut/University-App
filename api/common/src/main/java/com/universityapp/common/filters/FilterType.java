package com.universityapp.common.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterType {
    AND("AND"), OR("OR");

    private String value;
}
