package com.universityapp.users.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")

public class User {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String email;

}
