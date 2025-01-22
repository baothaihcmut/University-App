package com.universityapp.admin.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.universityapp.admin.dtos.internal.UpdateAdminDTO;
import com.universityapp.admin.repositories.IAdminRepository;
import com.universityapp.admin.repositories.impl.AdminFilterField;
import com.universityapp.auth.dtos.internal.AdminDTO;
import com.universityapp.auth.services.IAdminAuthService;
import com.universityapp.common.enums.FindByCriteriaType;
import com.universityapp.common.filters.FilterDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAuthService implements IAdminAuthService {
    private final IAdminRepository adminRepository;

    @Override
    public Optional<AdminDTO> findAdminByEmail(String email) {

        List<com.universityapp.admin.dtos.internal.AdminDTO> admins = this.adminRepository.findAdminByCriteria(
                List.of(FilterDTO.<AdminFilterField>builder().criterion(AdminFilterField.EMAIL).value(email)
                        .build()),
                FindByCriteriaType.AND);
        if (admins.size() == 0) {
            return Optional.empty();
        }
        com.universityapp.admin.dtos.internal.AdminDTO admin = admins.getFirst();
        AdminDTO res = AdminDTO.builder()
                .adminId(admin
                        .getAdminId())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .currentRefreshToken(admin.getCurrentRefreshToken())
                .build();
        return Optional.of(res);

    }

    @Override
    @Transactional
    public void updateAdminRefreshToken(UUID adminId, String refreshToken) {
        this.adminRepository.upadateAdmin(adminId, UpdateAdminDTO.builder().currentRefreshToken(refreshToken).build());
    }

}
