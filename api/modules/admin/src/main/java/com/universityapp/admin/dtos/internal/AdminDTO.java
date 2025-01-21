package com.universityapp.admin.dtos.internal;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AdminDTO {

    private UUID adminId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String currentRefreshToken;

    private String phoneNumber;
}
