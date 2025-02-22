package com.universityapp.modules.users.presenters.input;

import java.util.UUID;

import com.universityapp.common.enums.DependentType;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DependentDTO {
     @NotNull(message = "dependentType is not null")
     @NotBlank(message = "dependentType is not blank")
     private DependentType dependentType;
     @NotNull(message = "firstName is not null")
     @NotBlank(message = "firstName is not blank")
     private String firstName;
     @NotNull(message = "lastName is not null")
     @NotBlank(message = "lastName is not blank")
     private String lastName;
     @NotNull(message = "phoneNumber is not null")
     @NotBlank(message = "phoneNumber is not blank")
     private String phoneNumber;
}
