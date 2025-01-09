package com.universityapp.auth.dtos.response;

import com.universityapp.common.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private Role role;
    private Boolean isActive;

    private TokenResponse token;
}
