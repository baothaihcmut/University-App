package com.universityapp.common.sorts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SortDTO {
    private String field;
    private Boolean isAsc;
}
