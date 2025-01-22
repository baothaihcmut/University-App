package com.universityapp.faculities.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universityapp.common.response.AppResponse;
import com.universityapp.faculities.dtos.request.CreateFaculityRequestDTO;
import com.universityapp.faculities.dtos.request.UpdateFaculityRequestDTO;
import com.universityapp.faculities.dtos.response.CreateFaculityResponseDTO;
import com.universityapp.faculities.dtos.response.UpdateFaculityResponseDTO;
import com.universityapp.faculities.services.IFaculityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/faculities")
@RequiredArgsConstructor
public class FaculityController {
    private final IFaculityService faculityService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppResponse> createFaculity(@RequestBody @Valid CreateFaculityRequestDTO request) {
        CreateFaculityResponseDTO res = this.faculityService.createFaculity(request);
        return AppResponse.initResponse(HttpStatus.CREATED, true, "create faculity success", res);
    }

    @PostMapping("/bulk-create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppResponse> bulkCreateFaculity(@RequestBody @Valid List<CreateFaculityRequestDTO> request) {
        List<CreateFaculityResponseDTO> res = this.faculityService.bulkCreateFaculities(request);
        return AppResponse.initResponse(HttpStatus.CREATED, true, "bulk create faculity sucess", res);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppResponse> updateFaculity(@PathVariable("id") UUID id,
            @RequestBody @Valid UpdateFaculityRequestDTO request) {
        UpdateFaculityResponseDTO res = this.faculityService.updateFaculity(id, request);
        return AppResponse.initResponse(HttpStatus.CREATED, true, "update faculity success", res);
    }
}
