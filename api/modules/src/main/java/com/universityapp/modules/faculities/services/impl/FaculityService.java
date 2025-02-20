package com.universityapp.modules.faculities.services.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.universityapp.modules.faculities.dtos.request.CreateFaculityRequestDTO;
import com.universityapp.modules.faculities.dtos.request.UpdateFaculityRequestDTO;
import com.universityapp.modules.faculities.dtos.response.CreateFaculityResponseDTO;
import com.universityapp.modules.faculities.dtos.response.UpdateFaculityResponseDTO;
import com.universityapp.modules.faculities.services.IFaculityService;

@Service
@RequiredArgsConstructor
public class FaculityService implements IFaculityService {

    @Override
    @Transactional
    public CreateFaculityResponseDTO createFaculity(
        CreateFaculityRequestDTO request
    ) {
        // map to faculity dto
        return null;
    }

    @Override
    @Transactional
    public List<CreateFaculityResponseDTO> bulkCreateFaculities(
        List<CreateFaculityRequestDTO> faculities
    ) {
        // map to list request
        return null;
    }

    @Override
    @Transactional
    public UpdateFaculityResponseDTO updateFaculity(
        UUID id,
        UpdateFaculityRequestDTO request
    ) {
        // map to internal
        return null;
    }
}
