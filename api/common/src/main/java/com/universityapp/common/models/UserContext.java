package com.universityapp.common.models;


import com.universityapp.common.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserContext {
    private String userId;
    private Role[] roles;
}
