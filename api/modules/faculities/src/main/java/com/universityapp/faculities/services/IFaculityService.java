package com.universityapp.faculities.services;

import java.util.List;
import java.util.UUID;

import com.universityapp.faculities.dtos.request.CreateFaculityRequestDTO;
import com.universityapp.faculities.dtos.request.UpdateFaculityRequestDTO;
import com.universityapp.faculities.dtos.response.CreateFaculityResponseDTO;
import com.universityapp.faculities.dtos.response.UpdateFaculityResponseDTO;

public interface IFaculityService {
    CreateFaculityResponseDTO createFaculity(CreateFaculityRequestDTO request);

    List<CreateFaculityResponseDTO> bulkCreateFaculities(List<CreateFaculityRequestDTO> faculities);

    UpdateFaculityResponseDTO updateFaculity(UUID id, UpdateFaculityRequestDTO request);
}
