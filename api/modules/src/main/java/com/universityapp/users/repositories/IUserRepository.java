package com.universityapp.users.repositories;

import java.util.List;
import java.util.UUID;

import com.universityapp.auth.dtos.internal.CreateUserDTO;
import com.universityapp.common.enums.FindByCriteriaType;
import com.universityapp.common.filters.FilterDTO;
import com.universityapp.users.dtos.internal.UpdateUserDTO;
import com.universityapp.users.dtos.internal.UserDTO;
import com.universityapp.users.repositories.impl.UserField;

public interface IUserRepository {
    void createUser(CreateUserDTO dto);

    void updateUser(UUID id, UpdateUserDTO dto);

    List<UserDTO> findUserByCriteria(List<FilterDTO<UserField>> dtos, FindByCriteriaType type);

    void updateInfoUser(UUID id, UserDTO user);

}
