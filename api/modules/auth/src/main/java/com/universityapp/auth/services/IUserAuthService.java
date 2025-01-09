package com.universityapp.auth.services;

import java.util.Optional;
import java.util.UUID;

import com.universityapp.auth.dtos.internal.UserDTO;
import com.universityapp.auth.dtos.request.SignUpRequestDTO;

public interface IUserAuthService {
    Optional<UserDTO> findUserByEmail(String email);

    Optional<UserDTO> findUserByPhoneNumber(String phoneNumber);

    UserDTO createUser(SignUpRequestDTO requestDTO);

    void updateCurrentRefreshToken(UUID userId, String newToken);
}
