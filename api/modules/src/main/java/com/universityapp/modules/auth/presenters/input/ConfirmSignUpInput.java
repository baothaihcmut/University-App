package com.universityapp.modules.auth.presenters.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmSignUpInput {

    @NotNull(message = "code is required")
    private String code;
}
