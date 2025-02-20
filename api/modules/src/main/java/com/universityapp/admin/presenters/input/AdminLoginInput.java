package com.universityapp.admin.presenters.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AdminLoginInput {
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email can't be empty")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password can't be empty")
    private String password;
}
