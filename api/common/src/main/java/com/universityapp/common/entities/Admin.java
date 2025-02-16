package com.universityapp.common.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @Column(name = "admin_id")
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(name = "current_refresh_token", columnDefinition = "TEXT", nullable = true)
    private String currentRefreshToken;

    @Column(name = "phone_number")
    private String phoneNumber;
}
