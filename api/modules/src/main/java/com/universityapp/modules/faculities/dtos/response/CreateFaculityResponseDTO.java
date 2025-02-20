package com.universityapp.modules.faculities.dtos.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateFaculityResponseDTO {
    @JsonProperty("faculity_id")
    private UUID faculityId;

    @JsonProperty("name")
    private String name;
}
