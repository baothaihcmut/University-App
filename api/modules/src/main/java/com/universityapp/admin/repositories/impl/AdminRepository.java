package com.universityapp.admin.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.universityapp.admin.dtos.internal.AdminDTO;
import com.universityapp.admin.dtos.internal.UpdateAdminDTO;
import com.universityapp.admin.repositories.IAdminRepository;
import com.universityapp.common.enums.FindByCriteriaType;
import com.universityapp.common.filters.FilterDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminRepository implements IAdminRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AdminDTO> findAdminByCriteria(List<FilterDTO<AdminFilterField>> dtos, FindByCriteriaType type) {
        StringBuilder condition = new StringBuilder();
        for (int i = 0; i < dtos.size(); ++i) {
            if (condition.length() != 0) {
                condition.append(type.getValue());
            }
            condition.append(
                    String.format("%s = %s",
                            dtos.get(i).getCriterion().getField(),
                            String.format(":value_%d", i)));

        }

        String sql = String.format("""
                SELECT
                    admin_id AS adminId,
                    first_name AS firstName,
                    last_name AS lastName,
                    email AS email,
                    password AS password,
                    current_refresh_token AS currentRefreshToken,
                    phone_number AS phoneNumber
                FROM admins
                WHERE (%s)
                LIMIT 1
                                """, condition.toString());
        Query query = this.entityManager.createNativeQuery(sql, AdminDTO.class);
        for (int i = 0; i < dtos.size(); ++i) {
            query.setParameter(String.format("value_%d", i), dtos.get(i).getValue());
        }
        List<AdminDTO> res = new ArrayList<>();
        for (Object user : query.getResultList()) {
            res.add((AdminDTO) user);
        }
        return res;
    }

    @Override
    public void upadateAdmin(UUID adminId, UpdateAdminDTO dto) {
        String sql = """
                UPDATE users
                SET first_name = COALESCE(:firstName, first_name),
                    last_name = COALESCE(:lastName, last_name),
                    email = COALESCE(:email, email),
                    password = COALESCE(:password, password),
                    phone_number = COALESCE(:phoneNumber, phone_number),
                    current_refresh_token = COALESCE(:currentRefreshToken, current_refresh_token)
                WHERE user_id = :userId
                """;

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", adminId);
        query.setParameter("firstName", dto.getFirstName());
        query.setParameter("lastName", dto.getLastName());
        query.setParameter("email", dto.getEmail());
        query.setParameter("password", dto.getPassword());
        query.setParameter("phoneNumber", dto.getPhoneNumber());
        query.setParameter("currentRefreshToken", dto.getCurrentRefreshToken());
        query.executeUpdate();
    }

}
