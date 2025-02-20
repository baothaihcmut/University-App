package com.universityapp.modules.auth.services;

import com.universityapp.common.enums.Role;
import com.universityapp.common.models.UserContext;

import java.util.UUID;

public interface AuthService {
    String genAccessToken(UUID userId, Role role) throws Exception;
    String genRefreshToken(UUID userId) throws Exception;
    String encodePassword(String password);
    boolean checkPasswordMatch(String password, String hashPassword);
    UserContext getUserContext();
}
