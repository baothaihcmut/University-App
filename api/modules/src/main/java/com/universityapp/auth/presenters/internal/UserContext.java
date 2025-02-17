package com.universityapp.auth.presenters.internal;
import java.util.UUID;

import com.universityapp.common.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserContext {
    private UUID id;
    private Role[] role;
}
