package com.universityapp.users.repositories.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.universityapp.users.dtos.projection.UserDTO;
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

    @Transactional
    public void insertUser(String email, String studentId) {
        UUID id = UUID.randomUUID();
        String sql = """
                INSERT INTO users VALUES (:id,:email)
                """;

        Query query = this.entityManager.createQuery(sql);
        query.setParameter("id", id);
        query.setParameter("email", email);
        query.executeUpdate();

        String studentSql = """
                INSERT INTO students(userId,studentId) VALUES (:id, :studentId)
                """;
        Query studentQuery = this.entityManager.createQuery(studentId);
        studentQuery.setParameter("id", id);
        studentQuery.setParameter("studentId", studentId);
        this.entityManager.createQuery(studentSql).executeUpdate();
    }
}
