package com.universityapp.auth.services;

import java.util.Optional;
import java.util.UUID;

import com.universityapp.auth.dtos.internal.CreateUserDTO;
import com.universityapp.auth.dtos.internal.UserDTO;

public interface IUserAuthService {
    Optional<UserDTO> findUserByEmail(String email);

    Optional<UserDTO> findUserByPhoneNumber(String phoneNumber);

    void createUser(CreateUserDTO requestDTO);

    void updateCurrentRefreshToken(UUID userId, String newToken);
}
