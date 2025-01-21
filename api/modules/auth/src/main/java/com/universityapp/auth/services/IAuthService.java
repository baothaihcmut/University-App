package com.universityapp.auth.services;

import com.universityapp.auth.dtos.request.LoginRequestDTO;
import com.universityapp.auth.dtos.request.SignUpRequestDTO;
import com.universityapp.auth.dtos.response.LoginResponseDTO;

public interface IAuthService {
    LoginResponseDTO logIn(LoginRequestDTO requestDTO) throws Exception;

    LoginResponseDTO signUp(SignUpRequestDTO dto) throws Exception;

    LoginResponseDTO adminLogin(LoginRequestDTO requestDTO) throws Exception;
}
