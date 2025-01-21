package com.universityapp.faculities.services;

import java.util.List;

import com.universityapp.faculities.dtos.request.CreateFaculityRequestDTO;
import com.universityapp.faculities.dtos.response.CreateFaculityResponseDTO;

public interface IFaculityService {
    CreateFaculityResponseDTO createFaculity(CreateFaculityRequestDTO request);

    List<CreateFaculityResponseDTO> bulkCreateFaculities(List<CreateFaculityRequestDTO> faculities);
}
