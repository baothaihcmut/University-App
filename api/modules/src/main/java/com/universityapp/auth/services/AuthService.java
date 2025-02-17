package com.universityapp.auth.services;

import java.util.UUID;

import com.universityapp.common.enums.Role;

public interface AuthService {
    String genAccessToken(UUID userId, Role role) throws Exception;
    String genRefreshToken(UUID userId) throws Exception;
    String encodePassword(String password);
    boolean checkPasswordMatch(String password,String hashPassword);
}

