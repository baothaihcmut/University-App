package com.universityapp.auth.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.universityapp.auth.presenters.input.SignUpInput;
import com.universityapp.auth.presenters.internal.CreateUserDTO;
import com.universityapp.common.enums.Role;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "role", expression = "java(mapRole(requestDTO.getRole()))")
    CreateUserDTO toCreateUserDTO(SignUpInput requestDTO);

    default Role mapRole(String src) {
        return Role.valueOf(src);
    }
}
