package com.universityapp.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FindByCriteriaType {
    AND("AND"),
    OR("OR");

    private String value;
}
