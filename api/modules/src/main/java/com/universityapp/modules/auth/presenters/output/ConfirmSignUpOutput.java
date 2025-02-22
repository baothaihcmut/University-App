package com.universityapp.modules.auth.presenters.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.universityapp.common.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmSignUpOutput {
    @JsonProperty("role")
    private Role role;

    @JsonProperty("token")
    private TokenResponse token;
}
