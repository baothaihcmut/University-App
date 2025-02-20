package com.universityapp.faculities.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import com.universityapp.faculities.dtos.internal.CreateFaculityDTO;
import com.universityapp.faculities.dtos.internal.FaculityDTO;
import com.universityapp.faculities.dtos.internal.UpdateFaculityDTO;

public interface IFaculityRepository {
    void createFaculity(CreateFaculityDTO dto);

    void bulkCreateFaculity(List<CreateFaculityDTO> dtos);

    void updateFaculity(UUID id, UpdateFaculityDTO dto);

    Optional<FaculityDTO> findFaculityById(UUID id);

   

}
