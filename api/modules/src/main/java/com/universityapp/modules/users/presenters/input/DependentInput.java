package com.universityapp.modules.users.presenters.input;

import java.util.List;

import com.universityapp.common.enums.DependentType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DependentInput {
     public static class DependentDTO {
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

     @Valid
     List<DependentDTO> dependents;
}
