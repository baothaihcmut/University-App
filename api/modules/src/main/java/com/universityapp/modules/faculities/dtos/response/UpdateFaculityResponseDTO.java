package com.universityapp.modules.faculities.dtos.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFaculityResponseDTO {
    @JsonProperty("faculity_id")
    private UUID faculityId;

    private String name;
}
