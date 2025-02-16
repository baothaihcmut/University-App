package com.universityapp.users.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.universityapp.auth.dtos.internal.CreateUserDTO;
import com.universityapp.common.enums.FindByCriteriaType;
import com.universityapp.common.filters.FilterDTO;
import com.universityapp.users.dtos.internal.UpdateUserDTO;
import com.universityapp.users.dtos.internal.UserDTO;
import com.universityapp.users.repositories.IUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class UserRepository implements IUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void findAllUser() {
        String sql = """
                SELECT user_id AS userId,email AS email  FROM users
                """;

        Query query = entityManager.createNativeQuery(sql, UserDTO.class);
        System.out.println(((UserDTO) query.getResultList().getFirst()).getUserId());

    }

    public void createUser(CreateUserDTO dto) {
        String sql = """
                INSERT INTO users (user_id, first_name, last_name, email, password, phone_number, birthplace, birthday, social_network_info, address, role, is_active,current_refresh_token)
                VALUES (:userId, :firstName, :lastName, :email, :password, :phoneNumber, :birthplace, :birthday, :socialNetworkInfo, :address, :role, :isActive,:currentRefreshToken)
                """;

        Query query = this.entityManager.createNativeQuery(sql);
        query.setParameter("userId", dto.getUserId());
        query.setParameter("firstName", dto.getFirstName());
        query.setParameter("lastName", dto.getLastName());
        query.setParameter("email", dto.getEmail());
        query.setParameter("password", dto.getPassword());
        query.setParameter("phoneNumber", dto.getPhoneNumber());
        query.setParameter("birthplace", dto.getBirthplace());
        query.setParameter("birthday", dto.getBirthday());
        query.setParameter("socialNetworkInfo", dto.getSocialNetworkInfo());
        query.setParameter("address", dto.getAddress());
        query.setParameter("role", dto.getRole().getValue());
        query.setParameter("isActive", dto.getIsActive());
        query.setParameter("currentRefreshToken", dto.getCurrentRefreshToken());
        query.executeUpdate();
    }

    @Transactional
    public void updateUser(UUID id, UpdateUserDTO dto) {
        String sql = """
                UPDATE users
                SET first_name = COALESCE(:firstName, first_name),
                    last_name = COALESCE(:lastName, last_name),
                    email = COALESCE(:email, email),
                    password = COALESCE(:password, password),
                    phone_number = COALESCE(:phoneNumber, phone_number),
                    birthplace = COALESCE(:birthplace, birthplace),
                    birthday = COALESCE(:birthday, birthday),
                    social_network_info = COALESCE(:socialNetworkInfo, social_network_info),
                    address = COALESCE(:address, address),
                    role = COALESCE(:role, role),
                    is_active = COALESCE(:isActive, is_active),
                    current_refresh_token = COALESCE(:currentRefreshToken, current_refresh_token)
                WHERE user_id = :userId
                """;

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", id);
        query.setParameter("firstName", dto.getFirstName());
        query.setParameter("lastName", dto.getLastName());
        query.setParameter("email", dto.getEmail());
        query.setParameter("password", dto.getPassword());
        query.setParameter("phoneNumber", dto.getPhoneNumber());
        query.setParameter("birthplace", dto.getBirthplace());
        query.setParameter("birthday", dto.getBirthday());
        query.setParameter("socialNetworkInfo", dto.getSocialNetworkInfo());
        query.setParameter("address", dto.getAddress());
        query.setParameter("role", dto.getRole());
        query.setParameter("isActive", dto.getIsActive());
        query.setParameter("currentRefreshToken", dto.getCurrentRefreshToken());

        query.executeUpdate();
    }

    public List<UserDTO> findUserByCriteria(List<FilterDTO<UserField>> dtos, FindByCriteriaType type) {
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
                    user_id AS userId,
                    first_name AS firstName,
                    last_name AS lastName,
                    email AS email,
                    password AS password,
                    current_refresh_token AS currentRefreshToken,
                    phone_number AS phoneNumber,
                    image_id AS imageId,
                    birthplace AS birthplace,
                    birthday AS birthday,
                    social_network_info AS socialNetworkInfo,
                    address AS address,
                    role AS role,
                    is_active AS isActive
                FROM users
                WHERE (%s)
                                """, condition.toString());

        Query query = this.entityManager.createNativeQuery(sql, UserDTO.class);
        for (int i = 0; i < dtos.size(); ++i) {
            query.setParameter(String.format("value_%d", i), dtos.get(i).getValue());
        }
        List<UserDTO> res = new ArrayList<>();
        for (Object user : query.getResultList()) {
            res.add((UserDTO) user);
        }
        return res;

    }

    public void test() {
        String sql = """
                SELECT
                    u.user_id AS userId,
                    u.first_name AS firstName,
                    u.last_name AS lastName,
                    u.email AS email,
                    u.phone_number AS phoneNumber,
                    u.image_id AS image,
                    u.birthplace AS birthplace,
                    u.birthday AS birthday,
                    u.social_network_info AS socialNetworkInfo,
                    u.address AS address,
                    u.role AS role,
                    u.is_active AS isActive
                FROM users u
                """;
        Query q = this.entityManager.createNativeQuery(sql, UserDTO.class);
        System.out.println((UserDTO) q.getSingleResult());
    }

    @Transactional
    public void updateInfoUser(UUID id, UserDTO user) {

    }
}
