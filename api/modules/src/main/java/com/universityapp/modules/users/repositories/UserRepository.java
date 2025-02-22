package com.universityapp.modules.users.repositories;



import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.universityapp.modules.users.entities.User;



@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    @Query("SELECT u from User u WHERE u.email = :email AND u.isActive=:isActive")
    Optional<User> findUserByEmailAndIsActive(@Param("email") String email,@Param("isActive") boolean isActive);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);


    @Query("SELECT u from User u RIGHT JOIN FETCH Student s")
    Page<User> findAllStudent(Pageable pageable);


    @Query("SELECT u from User u RIGHT JOIN FETCH Teacher t")
    Page<User> findAllTeacher(Pageable pageable);

}
