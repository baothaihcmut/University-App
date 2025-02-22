package com.universityapp.modules.users.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.universityapp.modules.users.entities.Student;

@Repository
public interface StudentRespository extends JpaRepository<Student, UUID> {

     @Query("SELECT s from Student s  WHERE s.userId = :userId")
     Optional<Student> findStudentById(@Param("userId") String userId);
}
