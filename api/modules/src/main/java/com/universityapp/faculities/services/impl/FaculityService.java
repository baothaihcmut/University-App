package com.universityapp.faculities.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;


import com.universityapp.faculities.dtos.request.CreateFaculityRequestDTO;
import com.universityapp.faculities.dtos.request.UpdateFaculityRequestDTO;
import com.universityapp.faculities.dtos.response.CreateFaculityResponseDTO;
import com.universityapp.faculities.dtos.response.UpdateFaculityResponseDTO;
import com.universityapp.faculities.mappers.FaculityMapper;
import com.universityapp.faculities.repositories.IFaculityRepository;
import com.universityapp.faculities.services.IFaculityService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaculityService implements IFaculityService {
    private final IFaculityRepository faculityRepository;
    private final FaculityMapper faculityMapper;

    @Override
    @Transactional
    public CreateFaculityResponseDTO createFaculity(CreateFaculityRequestDTO request) {
        // map to faculity dto
       return null;
    }

    @Override
    @Transactional
    public List<CreateFaculityResponseDTO> bulkCreateFaculities(List<CreateFaculityRequestDTO> faculities) {
        // map to list request
        return null;
    }

    @Override
    @Transactional
    public UpdateFaculityResponseDTO updateFaculity(UUID id, UpdateFaculityRequestDTO request) {
        // map to internal
        return null;
    }

}
