package com.universityapp.faculities.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.universityapp.common.filters.FilterDTO;
import com.universityapp.common.filters.FilterType;
import com.universityapp.common.pagination.PaginationDTO;
import com.universityapp.common.pagination.PaginationResultDTO;
import com.universityapp.common.sorts.SortDTO;
import com.universityapp.faculities.dtos.internal.CreateFaculityDTO;
import com.universityapp.faculities.dtos.internal.FaculityDTO;
import com.universityapp.faculities.dtos.internal.UpdateFaculityDTO;
import com.universityapp.faculities.repositories.impl.FaculityFilterField;

public interface IFaculityRepository {
    void createFaculity(CreateFaculityDTO dto);

    void bulkCreateFaculity(List<CreateFaculityDTO> dtos);

    void updateFaculity(UUID id, UpdateFaculityDTO dto);

    Optional<FaculityDTO> findFaculityById(UUID id);

    PaginationResultDTO<FaculityDTO> findFaculityByCriteria(
            List<FilterDTO<FaculityFilterField>> filters,
            FilterType type,
            List<SortDTO> sorts,
            PaginationDTO pagination);

}
