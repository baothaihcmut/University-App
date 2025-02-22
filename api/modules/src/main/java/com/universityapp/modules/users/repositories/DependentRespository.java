package com.universityapp.modules.users.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.universityapp.modules.users.entities.Dependent;
import java.util.List;
import java.util.Optional;

@Repository
public interface DependentRespository extends JpaRepository<Dependent, UUID> {
     @Query("SELECT d FROM Dependent d WHERE d.dependentId = :dependentId AND d.user.userId = :userId")
     Optional<Dependent> findByUserIdAndDependentId(@Param("userId") UUID userId,
               @Param("dependentId") UUID dependentId);

     @Query("SELECT d FROM Dependent d WHERE  d.user.userId = :userId")
     List<Dependent> findByUserId(@Param("userId") UUID userId);
}
