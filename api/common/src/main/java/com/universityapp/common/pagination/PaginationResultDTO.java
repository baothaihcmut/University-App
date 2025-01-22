package com.universityapp.common.pagination;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResultDTO<T> {
    private List<T> data;
    private Integer currentPage;
    private Integer totalPage;
    private Integer totalElement;
}
