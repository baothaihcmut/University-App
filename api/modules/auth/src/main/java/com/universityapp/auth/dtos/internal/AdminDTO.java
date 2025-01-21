package com.universityapp.auth.dtos.internal;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AdminDTO {
    private UUID adminId;
    private String email;
    private String password;
    private String currentRefreshToken;

}
