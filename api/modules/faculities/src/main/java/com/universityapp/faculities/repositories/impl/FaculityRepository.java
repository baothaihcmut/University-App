package com.universityapp.faculities.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.universityapp.common.filters.FilterDTO;
import com.universityapp.common.filters.FilterType;
import com.universityapp.common.pagination.PaginationDTO;
import com.universityapp.common.pagination.PaginationResultDTO;
import com.universityapp.common.sorts.SortDTO;
import com.universityapp.common.utils.FilterHelper;
import com.universityapp.faculities.dtos.internal.CreateFaculityDTO;
import com.universityapp.faculities.dtos.internal.FaculityDTO;
import com.universityapp.faculities.dtos.internal.UpdateFaculityDTO;
import com.universityapp.faculities.repositories.IFaculityRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FaculityRepository implements IFaculityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createFaculity(CreateFaculityDTO dto) {
        String query = """
                INSERT INTO faculities(faculity_id,name)
                VALUES (
                    :id, :name
                )
                """;
        Query q = this.entityManager.createNativeQuery(query);
        q.setParameter("id", dto.getFaculityId());
        q.setParameter("name", dto.getName());
        q.executeUpdate();
    }

    @Override
    public void updateFaculity(UUID id, UpdateFaculityDTO dto) {
        String query = """
                UPDATE faculities
                SET
                    name = COALESCE(:name, name)
                WHERE
                    faculity_id = :id
                """;
        Query q = this.entityManager.createNativeQuery(query);
        q.setParameter("id", id);
        q.setParameter("name", dto.getName());
        q.executeUpdate();
    }

    @Override
    public Optional<FaculityDTO> findFaculityById(UUID id) {
        String query = """
                SELECT
                    f.faculity_id as faculityId,
                    f.name as name
                FROM faculities f
                WHERE f.faculity_id = :id
                LIMIT 1
                """;
        Query q = this.entityManager.createNativeQuery(query, FaculityDTO.class);
        try {
            FaculityDTO res = (FaculityDTO) q.getSingleResult();
            return Optional.of(res);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void bulkCreateFaculity(List<CreateFaculityDTO> dtos) {

        String query = """
                INSERT INTO faculities (faculity_id,name)
                SELECT * FROM unnest(:ids,:names)
                """;
        Query q = this.entityManager.createNativeQuery(query);
        q.setParameter("ids", dtos.stream().map(dto -> dto.getFaculityId()).toArray(UUID[]::new));
        q.setParameter("names", dtos.stream().map(dto -> dto.getName()).toArray(String[]::new));
        q.executeUpdate();
    }

    @Override
    public PaginationResultDTO<FaculityDTO> findFaculityByCriteria(
            List<FilterDTO<FaculityFilterField>> filters,
            FilterType type,
            List<SortDTO> sorts, PaginationDTO pagination) {
        String baseQuery = """
                SELECT
                    f.faculity_id as faculityId,
                    f.name as name
                FROM faculities f
                """;
        // add where to query
        baseQuery = filters != null ? FilterHelper.<FaculityFilterField>addFilterToQuery(baseQuery, filters, type)
                : baseQuery;

        // add order by to query
        baseQuery = filters != null ? FilterHelper.addSortToQuery(baseQuery, sorts) : baseQuery;
        Query query = this.entityManager.createNamedQuery(baseQuery, FaculityDTO.class);
        // set parameter for query
        query = filters != null ? FilterHelper.setParameterForFilter(query, filters) : query;
        // set pagination
        query = FilterHelper.setPaginationParam(query, pagination);
        var resList = query.getResultList();
        List<FaculityDTO> faculityDTOs = new ArrayList<>();
        for (var res : resList) {
            faculityDTOs.add((FaculityDTO) res);
        }
        // cout
        String count = "SELECT COUNT(*) FROM faculities";
        Query countQuery = this.entityManager.createNativeQuery(count, Integer.class);
        Integer totalElement = (Integer) countQuery.getSingleResult();
        return PaginationResultDTO.<FaculityDTO>builder().data(faculityDTOs)
                .currentPage(pagination.getPage())
                .totalPage(totalElement / pagination.getSize())
                .totalElement(totalElement)
                .build();
    }

}
