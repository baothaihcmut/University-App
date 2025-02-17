package com.universityapp.auth.presenters.internal;
import java.util.UUID;

import com.universityapp.common.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {
    private UUID userId;
    private String email;
    private String password;
    private Role role;
    private Boolean isActive;
    private String currentRefreshToken;
}
