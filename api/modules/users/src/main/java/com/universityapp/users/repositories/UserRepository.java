package com.universityapp.users.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.universityapp.users.dtos.projection.UserDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<UserDTO> findAllUser() {
        String sql = "SELECT user_id as id, email as email FROM users";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Transactional
    public void insertUser(String email) {
        String sql = """
                INSERT INTO users VALUES (:id,:email)
                """;
        Query query = this.entityManager.createNativeQuery(sql);
        query.setParameter("id", UUID.randomUUID());
        query.setParameter("email", email);
        query.executeUpdate();
    }
}
