package com.universityapp.users.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.universityapp.common.enums.Role;
import com.universityapp.files.entities.File;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "current_refresh_token", columnDefinition = "TEXT")
    private String currentRefreshToken;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "image_id", nullable = true)
    private File image;

    @Column(name = "birthplace", length = 255)
    private String birthplace;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "social_network_info", columnDefinition = "TEXT")
    private String socialNetworkInfo;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isActive;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Student student;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Teacher teacher;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Dependent> dependents;

}
