package com.universityapp.modules.faculities.dtos.internal;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaculityDTO {
    private UUID faculityId;
    private String name;
}
