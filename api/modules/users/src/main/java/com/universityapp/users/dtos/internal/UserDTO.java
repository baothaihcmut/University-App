package com.universityapp.users.dtos.internal;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import com.universityapp.common.enums.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserDTO {

    private UUID userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String currentRefreshToken;

    private String phoneNumber;

    private UUID imageId;

    private String birthplace;

    @Getter(AccessLevel.NONE)
    private Date birthday;

    private String socialNetworkInfo;

    private String address;

    @Getter(AccessLevel.NONE)
    private String role;

    private Boolean isActive;

    public LocalDate getBirthday() {
        return this.birthday.toLocalDate();
    }

    public Role getRole() {
        return Role.valueOf(this.role);
    }
}
