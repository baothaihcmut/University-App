package com.universityapp.auth.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.universityapp.auth.dtos.internal.CreateUserDTO;
import com.universityapp.auth.dtos.request.SignUpRequestDTO;
import com.universityapp.common.enums.Role;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "role", expression = "java(mapRole(requestDTO.getRole()))")
    CreateUserDTO toCreateUserDTO(SignUpRequestDTO requestDTO);

    default Role mapRole(String src) {
        return Role.valueOf(src);
    }
}
