package com.universityapp.modules.users.presenters.output;

import com.universityapp.common.enums.DependentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DependentOutput {

     private DependentType dependentType;

     private String firstName;

     private String lastName;

     private String phoneNumber;
}
