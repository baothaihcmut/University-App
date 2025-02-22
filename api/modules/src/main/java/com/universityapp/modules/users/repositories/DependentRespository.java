package com.universityapp.modules.users.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.universityapp.modules.users.entities.Dependent;

@Repository
public interface DependentRespository extends JpaRepository<Dependent, UUID> {

}
