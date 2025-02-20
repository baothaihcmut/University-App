package com.universityapp.modules.admin.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.universityapp.modules.admin.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,UUID>{

    @Query("SELECT a FROM Admin a WHERE a.email=:email")
    Optional<Admin> findAdminByEmail(@Param("email") String email);
}
