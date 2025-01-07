package com.universityapp.users.dtos.projection;

import java.util.UUID;

public interface UserDTO {
    UUID getUserId();

    String getEmail();
}
