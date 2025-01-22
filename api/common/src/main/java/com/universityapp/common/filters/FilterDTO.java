package com.universityapp.common.filters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterDTO<T> {
    private T criterion;
    private FilterOperator operator;
    private Object value;
}
