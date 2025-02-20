package com.universityapp.modules.faculities.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFaculityRequestDTO {
    @NotNull()
    @NotEmpty()
    private String name;
}
