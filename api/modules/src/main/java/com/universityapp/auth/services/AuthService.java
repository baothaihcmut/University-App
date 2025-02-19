package com.universityapp.auth.services;

import com.universityapp.auth.presenters.internal.UserContext;
import com.universityapp.common.enums.Role;
import java.util.UUID;

public interface AuthService {
    String genAccessToken(UUID userId, Role role) throws Exception;
    String genRefreshToken(UUID userId) throws Exception;
    String encodePassword(String password);
    boolean checkPasswordMatch(String password, String hashPassword);
    UserContext getUserContext();
}
