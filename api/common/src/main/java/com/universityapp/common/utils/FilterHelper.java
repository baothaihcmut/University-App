package com.universityapp.common.utils;

import java.util.List;
import java.util.StringJoiner;

import com.universityapp.common.filters.FilterDTO;
import com.universityapp.common.filters.FilterType;
import com.universityapp.common.pagination.PaginationDTO;
import com.universityapp.common.sorts.SortDTO;

import jakarta.persistence.Query;

public class FilterHelper {
    public static <T> String addFilterToQuery(String baseQuery, List<FilterDTO<T>> filters, FilterType type) {
        StringJoiner sj = new StringJoiner(type.getValue(), "WHERE (", ")");
        for (int i = 0; i < filters.size(); ++i) {
            switch (filters.get(i).getOperator()) {
                case EQUAL:
                    sj.add(String.format("%s = :value_%d", filters.get(i).getCriterion(), i));
                    break;
                case NOT_EQUAL:
                    sj.add(String.format("%s != :value_%d", filters.get(i).getCriterion(), i));
                    break;
                case LIKE:
                    sj.add(String.format("%s LIKE %:value_%d%", filters.get(i).getCriterion(), i));
                    break;
                default:
                    break;
            }
        }
        return baseQuery + sj.toString();
    }

    public static String addSortToQuery(String baseQuery, List<SortDTO> sorts) {
        StringJoiner sj = new StringJoiner(",", "ORDER BY ", "");
        for (SortDTO sortDTO : sorts) {
            if (sortDTO.getIsAsc()) {
                sj.add(sortDTO.getField() + " ASC");
            } else {
                sj.add(sortDTO.getField() + " DESC");
            }
        }
        return baseQuery + sj.toString();
    }

    public static <T> Query setParameterForFilter(Query query, List<FilterDTO<T>> filters) {
        for (int i = 0; i < filters.size(); ++i) {
            query = query.setParameter(String.format("value_%d", i), filters.get(i).getValue());
        }
        return query;
    }

    public static <T> Query setPaginationParam(Query query, PaginationDTO paginationDTO) {
        int offset = (paginationDTO.getPage() - 1) * paginationDTO.getSize();
        int size = paginationDTO.getPage();
        return query.setFirstResult(offset).setMaxResults(size);
    }
}
