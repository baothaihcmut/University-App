package com.universityapp.faculities.dtos.internal;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateFaculityDTO {
    private UUID faculityId;
    private String name;
}
