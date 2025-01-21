package com.universityapp.faculities.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.universityapp.faculities.dtos.internal.CreateFaculityDTO;
import com.universityapp.faculities.dtos.request.CreateFaculityRequestDTO;
import com.universityapp.faculities.dtos.response.CreateFaculityResponseDTO;
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
        CreateFaculityDTO dto = this.faculityMapper.toCreateFaculityInternalDTO(UUID.randomUUID(), request);
        // generate id
        // save to db
        this.faculityRepository.createFaculity(dto);
        // map to response and return
        return this.faculityMapper.toCreateFaculityResponseDTO(dto);
    }

    @Override
    @Transactional
    public List<CreateFaculityResponseDTO> bulkCreateFaculities(List<CreateFaculityRequestDTO> faculities) {
        // map to list request
        List<CreateFaculityDTO> faculityDTOs = faculities.stream()
                .map(faculity -> this.faculityMapper.toCreateFaculityInternalDTO(UUID.randomUUID(), faculity)).toList();
        // bulk save to db
        this.faculityRepository.bulkCreateFaculity(faculityDTOs);
        // map to response and return
        return faculityDTOs.stream().map(faculity -> this.faculityMapper.toCreateFaculityResponseDTO(faculity))
                .toList();
    }

}
