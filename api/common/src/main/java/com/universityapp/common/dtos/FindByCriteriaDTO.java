package com.universityapp.common.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByCriteriaDTO<T> {
    private T criterion;
    private Object value;
}
