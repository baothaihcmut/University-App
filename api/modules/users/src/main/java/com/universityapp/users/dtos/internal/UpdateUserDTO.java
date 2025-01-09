package com.universityapp.users.dtos.internal;

import java.time.LocalDate;

import com.universityapp.common.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String birthplace;

    private LocalDate birthday;

    private String socialNetworkInfo;

    private String address;

    private Role role;

    private Boolean isActive;

    private String currentRefreshToken;

}
