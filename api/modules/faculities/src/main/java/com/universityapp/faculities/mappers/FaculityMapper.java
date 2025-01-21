package com.universityapp.faculities.mappers;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.universityapp.faculities.dtos.internal.CreateFaculityDTO;
import com.universityapp.faculities.dtos.request.CreateFaculityRequestDTO;
import com.universityapp.faculities.dtos.response.CreateFaculityResponseDTO;

@Mapper(componentModel = "spring")
public interface FaculityMapper {
    @Mappings(value = {
            @Mapping(source = "id", target = "faculityId"),
            @Mapping(source = "request.name", target = "name")
    })
    CreateFaculityDTO toCreateFaculityInternalDTO(UUID id, CreateFaculityRequestDTO request);

    CreateFaculityResponseDTO toCreateFaculityResponseDTO(CreateFaculityDTO internal);
}
