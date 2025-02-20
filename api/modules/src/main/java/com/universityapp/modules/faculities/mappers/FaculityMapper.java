package com.universityapp.modules.faculities.mappers;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.universityapp.modules.faculities.dtos.internal.CreateFaculityDTO;
import com.universityapp.modules.faculities.dtos.internal.UpdateFaculityDTO;
import com.universityapp.modules.faculities.dtos.request.CreateFaculityRequestDTO;
import com.universityapp.modules.faculities.dtos.request.UpdateFaculityRequestDTO;
import com.universityapp.modules.faculities.dtos.response.CreateFaculityResponseDTO;
import com.universityapp.modules.faculities.dtos.response.UpdateFaculityResponseDTO;

@Mapper(componentModel = "spring")
public interface FaculityMapper {
    @Mappings(value = {
            @Mapping(source = "id", target = "faculityId"),
            @Mapping(source = "request.name", target = "name")
    })
    CreateFaculityDTO toCreateFaculityInternalDTO(UUID id, CreateFaculityRequestDTO request);

    CreateFaculityResponseDTO toCreateFaculityResponseDTO(CreateFaculityDTO internal);

    UpdateFaculityDTO toUpdateFaculityDTO(UpdateFaculityRequestDTO request);

    @Mappings(value = {
            @Mapping(source = "id", target = "faculityId"),
            @Mapping(source = "internal.name", target = "name")
    })
    UpdateFaculityResponseDTO toUpdateFaculityResponseDTO(UUID id, UpdateFaculityDTO internal);

}
