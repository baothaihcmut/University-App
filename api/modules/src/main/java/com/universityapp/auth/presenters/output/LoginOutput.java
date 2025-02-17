package com.universityapp.auth.presenters.output;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.universityapp.common.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginOutput {

    @JsonProperty("role")
    private Role role;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("token")
    private TokenResponse token;
}
