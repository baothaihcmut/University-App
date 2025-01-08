package com.universityapp.users.dtos.projection;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    UUID userId;

    String email;
}
