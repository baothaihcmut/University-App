package com.universityapp.admin.dtos.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateAdminDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String currentRefreshToken;

    private String phoneNumber;
}
