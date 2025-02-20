package com.universityapp.auth.mappers;

import com.universityapp.auth.presenters.input.SignUpInput;
import com.universityapp.common.enums.Role;
import com.universityapp.users.entities.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    @Mapping(
        target = "role",
        expression = "java(mapRole(requestDTO.getRole()))"
    )
    User toUser(SignUpInput requestDTO);

    default Role mapRole(String src) {
        return Role.valueOf(src);
    }
}
