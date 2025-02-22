package com.universityapp.modules.users.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.universityapp.common.enums.Role;
import com.universityapp.modules.auth.presenters.input.SignUpInput;
import com.universityapp.modules.users.entities.Dependent;
import com.universityapp.modules.users.entities.User;
import com.universityapp.modules.users.presenters.input.DependentDTO;
import com.universityapp.modules.users.presenters.output.DependentOutputDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
     DependentOutputDTO toDependentOutputDTO(Dependent dependent);

     Dependent toDependent(DependentDTO dependentDTO);

     List<Dependent> toDependentList(List<DependentDTO> dependentDTOs);

     List<DependentOutputDTO> toDependentOuputDTOList(List<Dependent> dependents);

     @Mapping(target = "role", expression = "java(mapRole(requestDTO.getRole()))")
     User toUser(SignUpInput requestDTO);

     default Role mapRole(String src) {
          return Role.valueOf(src);
     }
}
