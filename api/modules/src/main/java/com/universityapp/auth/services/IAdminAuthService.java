package com.universityapp.auth.services;
import java.util.Optional;
import java.util.UUID;

import com.universityapp.admin.dtos.internal.AdminDTO;


public interface IAdminAuthService {
    Optional<AdminDTO> findAdminByEmail(String email);

    void updateAdminRefreshToken(UUID adminId, String refreshToken);
}
