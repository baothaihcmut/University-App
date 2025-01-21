package com.universityapp.faculities.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateFaculityRequestDTO {
    @NotNull()
    @NotEmpty()
    private String name;
}
