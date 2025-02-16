package com.universityapp.admin.repositories;

import java.util.List;
import java.util.UUID;

import com.universityapp.admin.dtos.internal.AdminDTO;
import com.universityapp.admin.dtos.internal.UpdateAdminDTO;
import com.universityapp.admin.repositories.impl.AdminFilterField;
import com.universityapp.common.enums.FindByCriteriaType;
import com.universityapp.common.filters.FilterDTO;

public interface IAdminRepository {
    List<AdminDTO> findAdminByCriteria(List<FilterDTO<AdminFilterField>> dtos, FindByCriteriaType type);

    void upadateAdmin(UUID adminId, UpdateAdminDTO dto);
}
